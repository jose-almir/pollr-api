package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class OptionTest {
    @Test
    public void shouldCreateOptionProperly() {
        Option option = Option.of("My Option   ");

        assertEquals("My Option", option.getValue());
        assertEquals(0L, option.getVoteCount());
    }

    @Test
    public void shouldThrowWhenOptionValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Option option = Option.of(null);
        });
    }

    @Test
    public void shouldThrowWhenOptionValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Option option = Option.of("");
        });
    }

    @Test
    public void shouldBeEqualWhenIdsAreNullButValuesAreIdentical() {
        Option option1 = Option.of("A");
        Option option2 = Option.of("A");

        assertEquals(option1, option2);
    }

    @Test
    public void shouldBeEqualWhenValuesAndIdsAreIdentical() {
        Option option1 = Option.of("A");
        option1.setId(1L);
        Option option2 = Option.of("A");
        option2.setId(1L);

        assertEquals(option1, option2);
    }

    @Test
    public void shouldConsiderEqualWhenSameIdEvenWithDifferentValues() {
        Option option1 = Option.of("A");
        option1.setId(1L);
        Option option2 = Option.of("B");
        option2.setId(1L);

        assertEquals(option1, option2);
    }
}
