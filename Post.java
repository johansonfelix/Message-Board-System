package com.company;

import java.time.LocalDate;

public class Post {

    private String postID;
    private String message;
    private String user;
    private LocalDate date;
    boolean updated;

    public Post(String postID, String message, String user, LocalDate date) {
        this.postID = postID;
        this.message = message;
        this.user = user;
        this.date = date;
        updated= false;
    }

    public Post(String postID, String message, String user, LocalDate date, boolean updated) {
        this.postID = postID;
        this.message = message;
        this.user = user;
        this.date = date;
        this.updated = true;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID='" + postID + '\'' +
                ", message='" + message + '\'' +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", updated=" + updated +
                '}';
    }
}
