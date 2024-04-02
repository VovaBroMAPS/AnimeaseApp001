package com.example.animeaseapp001;

public class ProjectBackground {
    private long id;
    private String uri;

    public ProjectBackground(long id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
