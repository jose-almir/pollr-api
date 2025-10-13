package com.almirdev.pollr.unit.domain.vote;

import com.almirdev.pollr.domain.poll.*;
import com.almirdev.pollr.domain.user.User;
import com.almirdev.pollr.domain.vote.Vote;
import com.almirdev.pollr.domain.vote.VoteType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VoteTest {
    private Poll anonymousPoll;
    private Poll privatePoll;
    private Poll publicPoll;
    private User voter;

    @BeforeEach
    public void setUp() {
        voter = User.create("Bob Smith", "bob@example.com", "bob_smith", "secret");

        Set<Option> optionsSet = Set.of(
                Option.of("A"),
                Option.of("B")
        );

        PollOptions options = PollOptions.of(optionsSet);
        User creator = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");
        publicPoll = Poll.create("Poll #1", options, creator);

        User member = User.create("Jane Doe", "jane@example.com", "jane_doe", "secret");
        Set<User> membersSet = Set.of(member);
        PollMembers members = PollMembers.of(membersSet);
        privatePoll = Poll.createPrivate("Poll #2", options, members, creator);

        PollSettings anonymousSetting = PollSettings.of(
          AccessLevel.PUBLIC,
          VotePermission.ANONYMOUS,
          ResultsVisibility.ALL,
          Status.ACTIVE,
          null,
          null
        );

        anonymousPoll = Poll.create("Poll #3", options, creator);
        anonymousPoll.changeSettings(anonymousSetting);
    }


    @Test
    public void shouldCreateAuthenticatedVoteProperly() {
        Option chosenOption = Option.of("A");

        Vote vote = Vote.create(publicPoll, voter, chosenOption);

        assertEquals(VoteType.AUTHENTICATED, vote.getType());
        assertEquals(voter, vote.getUser());
        assertEquals(publicPoll, vote.getPoll());
    }

    @Test
    public void shouldCreateAnonymousVoteProperly() {
        Option chosenOption = Option.of("A");
        Vote vote = Vote.createAnonymous(anonymousPoll, chosenOption, UUID.randomUUID().toString());

        assertEquals(VoteType.ANONYMOUS, vote.getType());
        assertNull(vote.getUser());
        assertEquals(anonymousPoll, vote.getPoll());
    }

    @Test
    public void shouldThrowExceptionWhenVoterNotMemberPrivatePoll() {
        Option chosenOption = Option.of("A");

        assertThrows(IllegalStateException.class, () -> {
            Vote vote = Vote.create(privatePoll, voter, chosenOption);
        });
    }

    @Test
    public void shouldThrowExceptionWhenUserIsNullPublicPoll() {
        Option chosenOption = Option.of("A");

        assertThrows(IllegalArgumentException.class, () -> {
            Vote vote = Vote.create(publicPoll, null, chosenOption);
        });
    }

    @Test
    public void shouldThrowExceptionWhenAnonymousVoteOnNonAnonymousPoll() {
        Option option = Option.of("A");
        assertThrows(IllegalStateException.class, () -> {
            Vote.createAnonymous(publicPoll, option, UUID.randomUUID().toString());
        });
    }

    @Test
    public void shouldThrowExceptionWhenPollIsNull() {
        Option chosenOption = Option.of("A");

        assertThrows(IllegalArgumentException.class, () -> {
            Vote vote = Vote.create(null, voter, chosenOption);
        });
    }

    @Test
    public void shouldThrowWhenVotingForOptionNotInPoll() {
        Option wrongOption = Option.of("C");

        assertThrows(IllegalStateException.class, () -> {
            Vote vote = Vote.create(publicPoll, voter, wrongOption);
        });
    }

    @Test
    public void shouldThrowWhenChangingVoteToOptionNotInPoll() {
        Option initialOption = Option.of("A");
        Option wrongOption = Option.of("C");


        Vote vote = Vote.create(publicPoll, voter, initialOption);
        assertThrows(IllegalArgumentException.class, () -> {
            vote.changeOption(wrongOption);
        });
    }

    @Test
    public void shouldChangeOptionSuccessfully() {
        Option initial = Option.of("A");
        Option newOption = Option.of("B");

        Vote vote = Vote.create(publicPoll, voter, initial);
        vote.changeOption(newOption);

        assertEquals(newOption, vote.getOption());
    }
}
