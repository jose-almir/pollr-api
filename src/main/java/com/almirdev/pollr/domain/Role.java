package com.almirdev.pollr.domain;

public enum Role {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}
