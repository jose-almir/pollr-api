package com.almirdev.pollr.domain.poll;

import java.util.Set;

public class PollOptions {
    private final static int MAX_OPTIONS = 10;
    private Set<Option> values;

    protected PollOptions() {}

    private PollOptions(Set<Option> values) {
        this.values = Set.copyOf(values);
    }

    public static PollOptions of(Set<Option> values) {
        if(values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Polls must have at least one option.");
        }

        if(values.size() > MAX_OPTIONS) {
            throw new IllegalArgumentException("Poll cannot have more than 10 options.");
        }

        return new PollOptions(values);
    }

    public boolean contains(Option option) {
        return values.contains(option);
    }

    public int size() {
        return values.size();
    }

    public Set<Option> asSet() {
        return Set.copyOf(values);
    }

    public void setPoll(Poll poll) {
        for(Option opt : values) {
            opt.setPoll(poll);
        }
    }
}
