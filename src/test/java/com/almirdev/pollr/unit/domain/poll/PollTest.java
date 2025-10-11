package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.*;
import com.almirdev.pollr.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PollTest {
    @Test
    public void shouldCreatePublicPollWithDefaults() {
        assertDoesNotThrow(() -> {
            Set<Option> optionsSet = Set.of(
                    Option.of("A"),
                    Option.of("B")
            );
            PollOptions options = PollOptions.of(optionsSet);
            User creator = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");

            Poll poll = Poll.create("Poll #1", options, creator);

            assertNotNull(poll.getPublicId());
            assertEquals(Title.of("Poll #1"), poll.getTitle());
            assertEquals(options, poll.getOptions());
            assertEquals(0, poll.getMembers().size());
            assertEquals(AccessLevel.PUBLIC, poll.getSettings().getAccessLevel());
            assertEquals(VotePermission.AUTHENTICATED, poll.getSettings().getVotePermission());
            assertEquals(ResultsVisibility.ALL, poll.getSettings().getResultsVisibility());
            assertEquals(Status.ACTIVE, poll.getSettings().getStatus());
            assertEquals(creator, poll.getMetadata().getCreatedBy());
            assertNotNull(poll.getMetadata().getCreatedAt());

        });
    }

    @Test
    public void shouldCreatePrivatePollWithMembers() {
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");
        User member1 = User.create("Jane Doe", "jane@example.com", "jane_doe", "secret");
        User member2 = User.create("Bob Smith", "bob@example.com", "bob_smith", "secret");

        Set<User> membersSet = Set.of(member1, member2);

        Set<Option> optionsSet = Set.of(
                Option.of("A"),
                Option.of("B")
        );

        PollOptions options = PollOptions.of(optionsSet);
        PollMembers members = PollMembers.of(membersSet);

        Poll poll = Poll.createPrivate("Poll #2", options, members, creator);

        assertEquals(AccessLevel.PRIVATE, poll.getSettings().getAccessLevel());
        assertEquals(members, poll.getMembers());
    }

    @Test
    public void shouldChangeTitle() {
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");
        PollOptions options = PollOptions.of(Set.of(Option.of("A"), Option.of("B")));
        Poll poll = Poll.create("Title Antigo", options, creator);

        Title newTitle = Title.of("Novo Title");
        poll.changeTitle(newTitle);

        assertEquals(newTitle, poll.getTitle());
    }

    @Test
    public void shouldChangeSettings() {
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");
        PollOptions options = PollOptions.of(Set.of(Option.of("A"), Option.of("B")));
        Poll poll = Poll.create("Title", options, creator);

        PollSettings newSettings = PollSettings.privateDefaults();
        poll.changeSettings(newSettings);

        assertEquals(newSettings, poll.getSettings());
    }

    @Test
    public void shouldChangeMetadata() {
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");
        PollOptions options = PollOptions.of(Set.of(Option.of("A"), Option.of("B")));
        Poll poll = Poll.create("Title", options, creator);

        PollMetadata newMetadata = PollMetadata.getCreatedBy(creator);
        poll.changeMetadata(newMetadata);

        assertEquals(newMetadata, poll.getMetadata());
    }

    @Test
    public void pollsWithSamePublicIdShouldBeEqual() {
        PollOptions options = PollOptions.of(Set.of(Option.of("A"), Option.of("B")));
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");

        Poll poll1 = Poll.create("Title1", options, creator);
        Poll poll2 = Poll.create("Title2", options, creator);

        poll2.setPublicId(poll1.getPublicId());

        assertEquals(poll1, poll2);
        assertEquals(poll1.hashCode(), poll2.hashCode());
    }

    @Test
    public void shouldNotEqualPollsWithDifferentPublicIds() {
        PollOptions options = PollOptions.of(Set.of(Option.of("A"), Option.of("B")));
        User creator = User.create("John Doe", "john@example.com", "john_doe", "secret");

        Poll poll1 = Poll.create("Title1", options, creator);
        Poll poll2 = Poll.create("Title2", options, creator);

        assertNotEquals(poll1, poll2);
    }
}
