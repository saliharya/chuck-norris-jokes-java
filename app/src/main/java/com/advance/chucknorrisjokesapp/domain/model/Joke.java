package com.advance.chucknorrisjokesapp.domain.model;

public class Joke {
    private final String id;
    private final String value;
    private final String iconUrl;

    public Joke(String id, String value, String iconUrl) {
        this.id = id;
        this.value = value;
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
