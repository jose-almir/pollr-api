package com.almirdev.pollr.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Nickname {
    private static final Pattern NICKNAME_PATTERN = Pattern.compile(
            "^[A-Za-z0-9_]{5,15}$",
            Pattern.CASE_INSENSITIVE
    );

    private String value;

    protected Nickname() {
    }

    private Nickname(String value) {
        this.value = value;
    }

    public static Nickname of(String value) {
        if (value == null || !NICKNAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid nickname: " + value);
        }

        return new Nickname(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Nickname nickname)) return false;
        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Nickname{" +
                "value='" + value + '\'' +
                '}';
    }
}
