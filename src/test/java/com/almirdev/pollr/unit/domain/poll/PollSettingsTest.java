package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PollSettingsTest {
    @Test
    public void shouldCreateDefaultPollSettings() {
        PollSettings settings = PollSettings.defaultSettings();

        assertEquals(AccessLevel.PUBLIC, settings.getAccessLevel());
        assertEquals(VotePermission.AUTHENTICATED, settings.getVotePermission());
        assertEquals(ResultsVisibility.ALL, settings.getResultsVisibility());
    }

    @Test
    public void shouldCreatePrivatePollDefaultSettings() {
        PollSettings settings = PollSettings.privateDefaults();
        assertEquals(AccessLevel.PRIVATE, settings.getAccessLevel());
        assertEquals(VotePermission.AUTHENTICATED, settings.getVotePermission());
        assertEquals(ResultsVisibility.MEMBERS, settings.getResultsVisibility());
    }

    @Test
    public void shouldCreateCustomPollValidSettings() {
        assertDoesNotThrow(() -> {
            PollSettings settings = PollSettings.of(
                    AccessLevel.PRIVATE,
                    VotePermission.AUTHENTICATED,
                    ResultsVisibility.CREATOR,
                    Status.ACTIVE,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(5)
            );
        });
    }

    @Test
    public void shouldThrownExceptionWhenEndDateInPast() {
        LocalDateTime end = LocalDateTime.now().minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            PollSettings.of(AccessLevel.PUBLIC, VotePermission.AUTHENTICATED, ResultsVisibility.ALL,
                    Status.ACTIVE, null, end);
        });
    }

    @Test
    public void shouldThrowExceptionWhenEndBeforeStart() {
        LocalDateTime start = LocalDateTime.now().plusDays(2);
        LocalDateTime end = LocalDateTime.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            PollSettings.of(AccessLevel.PUBLIC, VotePermission.AUTHENTICATED, ResultsVisibility.ALL,
                    Status.ACTIVE, start, end);
        });
    }

    @Test
    public void shouldThrowExceptionForPrivateAnonymousPoll() {
        assertThrows(IllegalStateException.class, () -> {
            PollSettings.of(AccessLevel.PRIVATE, VotePermission.ANONYMOUS,
                    ResultsVisibility.MEMBERS, Status.ACTIVE, null, null);
        });
    }

    @Test
    void shouldThrowExceptionForPrivatePollVisibleToAll() {
        assertThrows(IllegalStateException.class, () -> {
            PollSettings.of(AccessLevel.PRIVATE, VotePermission.AUTHENTICATED,
                    ResultsVisibility.ALL, Status.ACTIVE, null, null);
        });
    }

    @Test
    void shouldThrowExceptionForPublicPollVisibleToMembers() {
        assertThrows(IllegalStateException.class, () -> {
            PollSettings.of(AccessLevel.PUBLIC, VotePermission.AUTHENTICATED,
                    ResultsVisibility.MEMBERS, Status.ACTIVE, null, null);
        });
    }

    @Test
    void equalsAndHashCodeShouldWork() {
        PollSettings s1 = PollSettings.defaultSettings();
        PollSettings s2 = PollSettings.defaultSettings();

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }
}
