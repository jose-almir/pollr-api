package com.almirdev.pollr.unit.domain;

import com.almirdev.pollr.domain.PublicId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PublicIdTest {
    @Test
    public void shouldGenerateUniqueRandomIds() {
        assertDoesNotThrow(() -> {
            PublicId id1 = PublicId.random();
            PublicId id2 = PublicId.random();

            assertNotEquals(id1, id2);
            assertEquals(10, id1.getValue().length());
        });
    }
}
