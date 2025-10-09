package com.almirdev.pollr.domain;

import java.util.Objects;

public class Name {
    private String value;

    protected Name() {}

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("User name can't be empty.");
        }

        String normalized = value.trim().replaceAll("\\s+", " ");

        if(normalized.length() < 2 || normalized.length() > 100) {
            throw new IllegalArgumentException("User name must have length between 2 and 100.");
        }

        if(!normalized.matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("User name contain invalid characters.");
        }

        return new Name(normalized);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Name name)) return false;
        return Objects.equals(getValue(), name.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
