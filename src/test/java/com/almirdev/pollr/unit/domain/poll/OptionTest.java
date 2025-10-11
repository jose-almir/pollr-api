package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OptionTest {
    @Test
    public void shouldCreateOptionProperly() {
        Option option = Option.of("My Option   ");

        assertEquals("My Option", option.getValue());
        assertEquals(0L, option.getVoteCount());
    }

    @Test
    public void shouldThrowExceptionWhenOptionValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Option option = Option.of(null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenOptionValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Option option = Option.of("");
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Option option1 = Option.of("A");
        Option option2 = Option.of("A");


        assertEquals(option1, option2);
    }
}
