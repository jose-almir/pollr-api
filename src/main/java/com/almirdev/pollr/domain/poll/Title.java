package com.almirdev.pollr.domain.poll;

import java.util.Objects;

public class Title {
    private String value;

    protected Title() {
    }

    private Title(String value) {
        this.value = value;
    }

    public static Title of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Poll title can't be empty.");
        }

        String normalized = value.trim();

        if (normalized.length() > 200) {
            throw new IllegalArgumentException("Poll title must not exceed 200 characters.");
        }

        return new Title(normalized);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Title title)) return false;
        return Objects.equals(getValue(), title.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return "Title{" +
                "value='" + value + '\'' +
                '}';
    }
}
