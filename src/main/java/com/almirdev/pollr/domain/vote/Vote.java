package com.almirdev.pollr.domain.vote;

import com.almirdev.pollr.domain.poll.AccessLevel;
import com.almirdev.pollr.domain.poll.Option;
import com.almirdev.pollr.domain.poll.Poll;
import com.almirdev.pollr.domain.poll.VotePermission;
import com.almirdev.pollr.domain.user.User;

import java.time.LocalDateTime;

public class Vote {
    private Long id;
    private Poll poll;
    private User user;
    private Option option;
    private String anonymousToken;
    private VoteType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Vote() {}

    private Vote(VoteType type, Poll poll, User user, Option option, String anonymousToken) {
        this.type = type;
        this.poll = poll;
        this.user = user;
        this.option = option;
        this.anonymousToken = anonymousToken;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Vote create(Poll poll, User user, Option option) {
        validatePollAndOption(poll, option);

        if(user == null) {
            throw new IllegalArgumentException("User can't be null on a authenticated vote");
        }

        if(poll.getSettings().getAccessLevel() == AccessLevel.PRIVATE && !poll.getMembers().isMember(user)) {
            throw new IllegalStateException("Can't vote. User don't have permission.");
        }

        return new Vote(VoteType.AUTHENTICATED, poll, user, option, null);
    }

    public static Vote createAnonymous(Poll poll, Option option, String anonymousToken) {
        validatePollAndOption(poll, option);

        if(poll.getSettings().getVotePermission() != VotePermission.ANONYMOUS) {
            throw new IllegalStateException("Anonymous vote only on a valid poll");
        }

        return new Vote(VoteType.ANONYMOUS, poll, null, option, anonymousToken);
    }

    private static void validatePollAndOption(Poll poll, Option option) {
        if(poll == null) {
            throw new IllegalArgumentException("Poll can't be null on a vote");
        }

        if(!poll.getOptions().contains(option)) {
            throw new IllegalStateException("Poll doesn't have that option");
        }
    }

    public void changeOption(Option newOption) {
        if(!poll.getOptions().contains(newOption)) {
            throw new IllegalArgumentException("Poll doesn't have that option");
        }
        this.option = newOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public User getUser() {
        return user;
    }

    public Option getOption() {
        return option;
    }

    public String getAnonymousToken() {
        return anonymousToken;
    }

    public VoteType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
