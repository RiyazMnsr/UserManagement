package com.example.user.error;

import java.time.LocalDateTime;

public class UserError {

    private LocalDateTime localDateTime;
    private String message;

    public UserError() {
        super();
    }

    public UserError(LocalDateTime localDateTime, String message) {
        this.localDateTime = localDateTime;
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
