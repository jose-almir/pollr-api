package com.almirdev.pollr.domain.poll;

import com.almirdev.pollr.domain.user.User;

import java.util.Objects;

public class Option {
    private Long id;
    private String value;
    private Long voteCount;
    private Poll poll;

    protected Option() {}

    private Option(String value, Long voteCount) {
        this.value = value;
        this.voteCount = voteCount;
    }

    public static Option of(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Option value can't be empty");
        }

        String normalized = value.trim();
        return new Option(normalized, 0L);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option option)) return false;

        if (id != null && option.id != null) {
            return Objects.equals(id, option.id);
        }

        return Objects.equals(value, option.value);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
