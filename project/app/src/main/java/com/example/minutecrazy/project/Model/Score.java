package com.example.minutecrazy.project.Model;

public class Score {
    private String id,username,score,categoryId;

    public Score() {
    }

    public Score(String id, String username, String score, String categoryId) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
