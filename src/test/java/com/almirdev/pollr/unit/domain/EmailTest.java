package com.almirdev.pollr.unit.domain;

import com.almirdev.pollr.domain.Email;
import com.almirdev.pollr.domain.Name;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    public void shouldCreateEmailWhenValueIsValid() {
        assertDoesNotThrow(() -> {
            String value = "john.doe@gmail.com";
            Email email = Email.of(value);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
           Email email = Email.of(null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Email email = Email.of("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsInvalid() {
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

    @Test
    public void shouldNormalizeWhenValueHasExtraSpaces() {
        String value = "John  Doe";
        Name name = Name.of(value);

        assertEquals("John Doe", name.getValue());
    }
}
