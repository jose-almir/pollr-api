package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Nickname;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NicknameTest {
    @Test
    public void shouldCreateNicknameWhenValueIsValid() {
        Nickname nickname = Nickname.of("johndoe");
    }

    @Test
    public void shouldThrowWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Nickname nickname = Nickname.of(null);
        });
    }

    @Test
    public void shouldThrowWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Nickname nickname = Nickname.of("");
        });
    }

    @Test
    public void shouldThrowWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidNickName = "johndoe+´^";
            Nickname nickname = Nickname.of(invalidNickName);
        });
    }

    @Test
    public void shouldThrowWhenValueLengthGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            String largeNickname = "averyverylargenickname";
            Nickname nickname = Nickname.of(largeNickname);
        });
    }

    @Test
    public void shouldBeEqualWhenValuesAreIdentical() {
        Nickname nickname1 = Nickname.of("johndoe");
        Nickname nickname2 = Nickname.of("johndoe");

        assertEquals(nickname1, nickname2);
    }
}
