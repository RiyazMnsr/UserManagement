package com.example.user.dto;

public class UserCountResponse {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserCountResponse [count="+count+"]";
    }
}
