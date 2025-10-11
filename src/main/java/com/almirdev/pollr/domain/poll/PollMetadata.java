package com.almirdev.pollr.domain.poll;

import com.almirdev.pollr.domain.user.User;

import java.time.LocalDate;
import java.util.Objects;

public class PollMetadata {
    private User createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    protected PollMetadata() {}

    private PollMetadata(User createdBy, LocalDate createdAt, LocalDate updatedAt) {
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PollMetadata getCreatedBy(User createdBy) {
        return new PollMetadata(createdBy, LocalDate.now(), LocalDate.now());
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PollMetadata) obj;
        return Objects.equals(this.createdBy, that.createdBy) &&
                Objects.equals(this.createdAt, that.createdAt) &&
                Objects.equals(this.updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "PollMetadata[" +
                "createdBy=" + createdBy + ", " +
                "createdAt=" + createdAt + ", " +
                "updatedAt=" + updatedAt + ']';
    }

}
