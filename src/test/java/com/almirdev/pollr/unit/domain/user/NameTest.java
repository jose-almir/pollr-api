package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    @Test
    public void shouldCreateNameWhenValueIsValid() {
        assertDoesNotThrow(() -> {
            Name name = Name.of("John Doe");
        });
    }

    @Test
    public void shouldThrowWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Name name = Name.of(null);
        });
    }

    @Test
    public void shouldThrowWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Name name = Name.of("");
        });
    }

    @Test
    public void shouldThrowWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidName = "John-Doe";
            Name name = Name.of(invalidName);
        });
    }

    @Test
    public void shouldThrowWhenValueLengthGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidName = "John";
            invalidName += " Doe".repeat(30);

            Name name = Name.of(invalidName);
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Name name1 = Name.of("John Doe");
        Name name2 = Name.of("John Doe");

        assertEquals(name1, name2);
    }

    @Test
    public void shouldNormalizeWhenValueHasExtraSpaces() {
        String value = "John  Doe";
        Name name = Name.of(value);

        assertEquals("John Doe", name.getValue());
    }
}
