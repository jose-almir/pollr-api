package com.almirdev.pollr.domain.poll;

import com.almirdev.pollr.domain.user.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PollGroup {
    private Set<User> members;

    protected PollGroup() {
    }

    private PollGroup(Set<User> members) {
        this.members = new HashSet<>(members);
    }

    public static PollGroup empty() {
        return new PollGroup(Set.of());
    }

    public static PollGroup of(Set<User> members) {
        if (members == null) {
            throw new IllegalArgumentException("Poll members can't be null.");
        }
        return new PollGroup(members);
    }

    public boolean add(User user) {
        return members.add(user);
    }

    public boolean remove(User user) {
        return members.remove(user);
    }

    public boolean isMember(User user) {
        return members.contains(user);
    }

    public Set<User> asSet() {
        return Collections.unmodifiableSet(members);
    }

    public int size() {
        return members.size();
    }
}
