package com.almirdev.pollr.domain.user;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;
    private Name name;
    private Email email;
    private Nickname nickname;
    private Password password;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected User() {
    }

    private User(Name name, Email email, Nickname nickname, Password password) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static User create(String name, String email, String nickname, String password) {
        User user = new User(Name.of(name), Email.of(email), Nickname.of(nickname), Password.ofPlainText(password));
        user.role = Role.USER;
        user.createdAt = LocalDateTime.now();
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateProfile(String newName, String newNickname) {
        this.name = Name.of(newName);
        this.nickname = Nickname.of(newNickname);
        this.updatedAt = LocalDateTime.now();
    }

    public void changeEmail(String newEmail) {
        this.email = Email.of(newEmail);
        this.updatedAt = LocalDateTime.now();
    }

    public void changePassword(String hashedPassword) {
        this.password = Password.ofHashed(hashedPassword);
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (id != null && user.id != null) {
            return Objects.equals(id, user.id);
        }

        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : Objects.hash(email);
    }
}
