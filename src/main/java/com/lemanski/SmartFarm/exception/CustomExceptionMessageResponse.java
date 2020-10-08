package com.lemanski.SmartFarm.exception;

public class CustomExceptionMessageResponse {

    private String message;

    public CustomExceptionMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
