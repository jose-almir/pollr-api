package com.almirdev.pollr.domain.poll;

import java.time.LocalDateTime;
import java.util.Objects;

public class PollSettings {
    private AccessLevel accessLevel;
    private VotePermission votePermission;
    private ResultsVisibility resultsVisibility;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    protected PollSettings() {
    }

    private PollSettings(AccessLevel accessLevel, VotePermission votePermission, ResultsVisibility resultsVisibility,
                         Status status, LocalDateTime startDate, LocalDateTime endDate) {
        this.accessLevel = accessLevel;
        this.votePermission = votePermission;
        this.resultsVisibility = resultsVisibility;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static PollSettings of(AccessLevel accessLevel, VotePermission votePermission, ResultsVisibility resultsVisibility,
                                  Status status, LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate != null && !endDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Poll end date must be in the future.");
        }

        if (startDate != null && endDate != null && !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Poll period must be valid");
        }

        if (accessLevel == AccessLevel.PRIVATE && votePermission == VotePermission.ANONYMOUS) {
            throw new IllegalStateException("Invalid poll settings. There's no PRIVATE and ANONYMOUS poll.");
        }

        if (accessLevel == AccessLevel.PRIVATE && resultsVisibility == ResultsVisibility.ALL) {
            throw new IllegalStateException("Invalid poll settings. There's no PRIVATE poll with results visibility for everyone.");
        }

        if (accessLevel == AccessLevel.PUBLIC && resultsVisibility == ResultsVisibility.MEMBERS) {
            throw new IllegalStateException("Invalid poll settings. There's no members in a public poll");
        }

        return new PollSettings(accessLevel, votePermission, resultsVisibility, status, startDate, endDate);
    }

    public static PollSettings defaultSettings() {
        return new PollSettings(AccessLevel.PUBLIC, VotePermission.AUTHENTICATED, ResultsVisibility.ALL, Status.ACTIVE, null, null);
    }

    public static PollSettings privateDefaults() {
        return new PollSettings(AccessLevel.PRIVATE, VotePermission.AUTHENTICATED, ResultsVisibility.MEMBERS, Status.ACTIVE, null, null);
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public VotePermission getVotePermission() {
        return votePermission;
    }

    public ResultsVisibility getResultsVisibility() {
        return resultsVisibility;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PollSettings) obj;
        return Objects.equals(this.accessLevel, that.accessLevel) &&
                Objects.equals(this.votePermission, that.votePermission) &&
                Objects.equals(this.resultsVisibility, that.resultsVisibility) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.startDate, that.startDate) &&
                Objects.equals(this.endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessLevel, votePermission, resultsVisibility, status, startDate, endDate);
    }

    @Override
    public String toString() {
        return "PollSettings[" +
                "accessLevel=" + accessLevel + ", " +
                "votePermission=" + votePermission + ", " +
                "resultsVisibility=" + resultsVisibility + ", " +
                "status=" + status + ", " +
                "startDate=" + startDate + ", " +
                "endDate=" + endDate + ']';
    }

}
