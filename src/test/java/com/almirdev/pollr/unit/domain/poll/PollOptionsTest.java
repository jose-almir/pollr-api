package com.almirdev.pollr.unit.domain.poll;

import com.almirdev.pollr.domain.poll.Option;
import com.almirdev.pollr.domain.poll.PollOptions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PollOptionsTest {
    @Test
    public void shouldCreatePollOptionsProperly() {
        assertDoesNotThrow(() -> {
            PollOptions options = PollOptions.of(mockOptionsSet(4));

            assertEquals(4, options.size());
            assertTrue(options.contains(Option.of("Option 1")));
        });
    }

    @Test
    public void shouldThrowWhenOptionsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            PollOptions options = PollOptions.of(null);
        });
    }

    @Test
    public void shouldThrowWhenOptionsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            PollOptions options = PollOptions.of(Set.of());
        });
    }

    @Test
    public void shouldThrowWhenOptionCountExceedsLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
           PollOptions options = PollOptions.of(mockOptionsSet(12)) ;
        });
    }

    private Set<Option> mockOptionsSet(int length) {
        Set<Option> options = new HashSet<>(length);
        for(int i = 0; i < length; i++) {
            Option option = Option.of("Option " + (i + 1));
            options.add(option);
        }

        return options;
    }
}
