package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Role;
import com.almirdev.pollr.domain.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void shouldCreateUserProperly() {
        User user = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");

        assertNotNull(user.getName());
        assertEquals("John Doe", user.getName().getValue());

        assertNotNull(user.getEmail());
        assertEquals("john.doe@example.com", user.getEmail().getValue());

        assertNotNull(user.getNickname());
        assertEquals("john_doe", user.getNickname().getValue());

        assertNotNull(user.getPassword());
        assertEquals("secret", user.getPassword().getValue());

        assertEquals(Role.USER, user.getRole());
        assertNotNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
    }

    @Test
    public void shouldUpdateNameAndNickNameAndSetUpdatedAt() throws InterruptedException {
        User user = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");
        LocalDate before = LocalDate.now();
        Thread.sleep(10);
        user.updateProfile("New Name", "new_nick");
        assertEquals("New Name", user.getName().getValue());
        assertEquals("new_nick", user.getNickname().getValue());
        assertNotNull(user.getUpdatedAt());
        assertTrue(user.getUpdatedAt().isAfter(before) || user.getUpdatedAt().isEqual(before));
    }

    @Test
    public void shouldUpdateEmailAndSetUpdatedAt() {
        User user = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");
        user.changeEmail("new@email.com");
        assertEquals("new@email.com", user.getEmail().getValue());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    public void shouldUpdatePasswordAndSetUpdatedAt() {
        User user = User.create("John Doe", "john.doe@example.com", "john_doe", "secret");
        user.changePassword("hashedSecret");
        assertEquals("hashedSecret", user.getPassword().getValue());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    public void shouldBeEqualWhenIdsAndEmailsAreIdentical() {
        User user1 = User.create("John Doe", "john.doe@example.com", "john_doe1", "secret1");
        User user2 = User.create("John Doe", "john.doe@example.com", "john_doe2", "secret2");

        user1.setId(1L);
        user2.setId(1L);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void shouldBeEqualWhenIdsAreNullButEmailsAreTheSame() {
        User user1 = User.create("John Doe", "john.doe@example.com", "johnny", "secret");
        User user2 = User.create("Jane Doe", "john.doe@example.com", "jane_doe", "123456");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void shouldBeEqualWhenIdsAreTheSameEvenIfEmailsDiffer() {
        User user1 = User.create("John Doe", "john1@example.com", "johnny", "secret");
        User user2 = User.create("John Doe", "john2@example.com", "johnny", "secret");

        user1.setId(42L);
        user2.setId(42L);

        assertEquals(user1, user2);
    }
}
