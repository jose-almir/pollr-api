package com.almirdev.pollr.domain.poll;

import java.security.SecureRandom;
import java.util.Objects;

public class PublicId {
    private static final int DEFAULT_LENGTH = 10;
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String value;

    protected PublicId() {}

    private PublicId(String value) {
        this.value = value;
    }

    public static PublicId random() {
        StringBuilder sb = new StringBuilder(DEFAULT_LENGTH);

        for(int i = 0; i < DEFAULT_LENGTH; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }

        return new PublicId(sb.toString());
    }

    public static PublicId of(String value) {
        return new PublicId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PublicId publicId)) return false;
        return Objects.equals(getValue(), publicId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return "PublicId{" +
                "value='" + value + '\'' +
                '}';
    }
}
