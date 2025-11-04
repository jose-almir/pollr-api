package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    public void shouldCreateEmailWhenValueIsValid() {
        assertDoesNotThrow(() -> {
            String value = "john.doe@gmail.com";
            Email email = Email.of(value);

            assertEquals(value, email.getValue());
        });
    }

    @Test
    public void shouldThrowWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Email email = Email.of(null);
        });
    }

    @Test
    public void shouldThrowWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Email email = Email.of("");
        });
    }

    @Test
    public void shouldThrowWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidValue = "john.doe.invalid";
            Email email = Email.of(invalidValue);
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Email email1 = Email.of("john.doe@example.com");
        Email email2 = Email.of("john.doe@example.com");

        assertEquals(email1, email2);
    }
}
