package com.almirdev.pollr.domain.poll;

import com.almirdev.pollr.domain.user.User;

import java.util.Objects;

public class Poll {
    private Long id;
    private PublicId publicId;
    private Title title;
    private PollOptions options;
    private PollGroup members;
    private PollSettings settings;
    private PollMetadata metadata;

    protected Poll() {
    }

    private Poll(String title, PollOptions options, PollGroup members, PollSettings settings, PollMetadata metadata) {
        this.publicId = PublicId.random();
        this.title = Title.of(title);
        this.options = options;
        this.members = members == null ? PollGroup.empty() : members;
        this.settings = settings == null ? PollSettings.defaultSettings() : settings;
        this.metadata = metadata;
        this.options.setPoll(this);
    }

    public static Poll create(String title, PollOptions options, User createdBy) {
        PollSettings settings = PollSettings.defaultSettings();
        PollMetadata metadata = PollMetadata.getCreatedBy(createdBy);
        return new Poll(title, options, null, settings, metadata);
    }

    public static Poll createPrivate(String title, PollOptions options, PollGroup members, User createdBy) {
        PollSettings privateSettings = PollSettings.privateDefaults();
        PollMetadata metadata = PollMetadata.getCreatedBy(createdBy);
        return new Poll(title, options, members, privateSettings, metadata);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublicId getPublicId() {
        return publicId;
    }

    public void setPublicId(PublicId publicId) {
        this.publicId = publicId;
    }

    public Title getTitle() {
        return title;
    }

    public PollOptions getOptions() {
        return options;
    }

    public PollGroup getMembers() {
        return members;
    }

    public PollSettings getSettings() {
        return settings;
    }

    public PollMetadata getMetadata() {
        return metadata;
    }

    public void changeTitle(Title newTitle) {
        this.title = newTitle;
    }

    public void changeSettings(PollSettings newSettings) {
        this.settings = newSettings;
    }

    public void changeMetadata(PollMetadata newMetadata) {
        this.metadata = newMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Poll poll)) return false;
        return Objects.equals(publicId, poll.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publicId);
    }
}