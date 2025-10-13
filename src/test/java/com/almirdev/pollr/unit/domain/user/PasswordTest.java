package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {
    @Test
    public void shouldCreatePasswordWhenPlainTextIsValid() {
        assertDoesNotThrow(() -> {
            String value = "12345678";
            Password password = Password.ofPlainText(value);

            assertEquals(value, password.getValue());
            assertFalse(password.isHashed());
        });
    }

    @Test
    public void shouldThrowWhenPlainTextIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password password = Password.ofPlainText(null);
        });
    }

    @Test
    public void shouldThrowWhenPlainTextIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Password password = Password.ofPlainText("");
        });
    }

    @Test
    public void shouldThrowWhenPlainTextLengthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidValue = "1234";
            Password password = Password.ofPlainText(invalidValue);
        });
    }

    @Test
    public void shouldCreatePasswordWhenHashIsDefined() {
        assertDoesNotThrow(() -> {
            String hashValue = "$2a$10$xyz...";
            Password password = Password.ofHashed(hashValue);

            assertEquals(hashValue, password.getValue());
            assertTrue(password.isHashed());
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Password password1 = Password.ofPlainText("12345678");
        Password password2 = Password.ofPlainText("12345678");

        assertEquals(password1, password2);
    }
}