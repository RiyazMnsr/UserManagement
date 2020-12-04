package com.example.user.dto;

import javax.validation.constraints.NotNull;

public class User {

    public User() {
        super();
    }

    public User(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @NotNull(message = "User Id must not null")
    private Integer userId;

    @NotNull(message = "Id must not null")
    private Integer id;

    private String title;

    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", id=" + id + ", title=" + title +", body=" +body+ "]";
    }
}
