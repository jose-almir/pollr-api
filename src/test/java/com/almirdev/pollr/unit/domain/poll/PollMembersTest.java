package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.PollMembers;
import com.almirdev.pollr.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PollMembersTest {
    private Set<User> mockMembersSet;

    @BeforeEach
    public void setUp() {
        User user1 = User.create("John Doe", "john1@example.com", "john1", "secret");
        User user2 = User.create("Jane Doe", "jane@example.com", "jane_doe", "secret");
        User user3 = User.create("Bob Smith", "bob@example.com", "bob_smith", "secret");
        User user4 = User.create("Alice Ray", "alice@example.com", "alice_ray", "secret");

        mockMembersSet = Set.of(user1, user2, user3, user4);
    }

    @Test
    public void shouldCreatePollMembersProperly() {
        assertDoesNotThrow(() -> {
            PollMembers members = PollMembers.of(mockMembersSet);

            assertEquals(4, members.size());
        });
    }

    @Test
    public void shouldThrowExceptionWhenMembersIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            PollMembers members = PollMembers.of(null);
        });
    }

    @Test
    public void shouldAddMemberProperly() {
        User newUser = User.create("Michael Johnson", "michael@example.com", "michael_john", "secret");
        PollMembers members = PollMembers.of(mockMembersSet);

        assertTrue(members.add(newUser));
        assertEquals(5, members.size());
        assertTrue(members.isMember(newUser));
    }

    @Test
    public void shouldRemoveMemberProperly() {
        User toRemove = User.create("John Doe", "john1@example.com", "john1", "secret");
        PollMembers members = PollMembers.of(mockMembersSet);

        assertTrue(members.remove(toRemove));
        assertEquals(3, members.size());
        assertFalse(members.isMember(toRemove));
    }
}
