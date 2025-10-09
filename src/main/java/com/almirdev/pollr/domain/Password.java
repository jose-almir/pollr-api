package com.almirdev.pollr.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Password {
    private String value;
    private boolean hashed = true;

    protected Password() {}

    private Password(String value, boolean hashed) {
        this.value = value;
        this.hashed = hashed;
    }

    public static Password ofHashed(String value) {
        return new Password(value, true);
    }

    public static Password ofPlainText(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Password can't be empty");
        }

        String normalized = value.trim();

        if(normalized.length() < 6) {
            throw new IllegalArgumentException("Password must have length above 6 characters");
        }

        return new Password(normalized, false);
    }

    public String getValue() {
        return value;
    }

    public boolean isHashed() {
        return hashed;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password)) return false;
        return Objects.equals(getValue(), password.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return "Password{" +
                "value='" + value + '\'' +
                '}';
    }
}
