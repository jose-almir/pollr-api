package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TitleTest {
    @Test
    public void shouldCreateTitleWhenValueIsValid() {
        assertDoesNotThrow(() -> {
            String value = "New Poll";
            Title title = Title.of(value);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Title name = Title.of(null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Title name = Title.of("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueLengthGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidValue = "A " + "very ".repeat(40) + "large poll title";
            Title name = Title.of(invalidValue);
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Title title1 = Title.of("My Poll");
        Title title2 = Title.of("My Poll");

        assertEquals(title1, title2);
    }
}
