package com.almirdev.pollr.domain.user;

public enum Role {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}
