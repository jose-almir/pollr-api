package com.almirdev.pollr.unit.domain.user;

import com.almirdev.pollr.domain.user.Nickname;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NicknameTest {
    @Test
    public void shouldCreateNicknameWhenValueIsValid() {
        Nickname nickname = Nickname.of("johndoe");
    }

    @Test
    public void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Nickname nickname = Nickname.of(null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
           Nickname nickname = Nickname.of("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidNickName = "johndoe+´^";
            Nickname nickname = Nickname.of(invalidNickName);
        });
    }

    @Test
    public void shouldThrowExceptionWhenValueLengthGreaterThanMax() {
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
