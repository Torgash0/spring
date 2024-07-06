package com.example.demo.modelDemo;


public class EventResponse {
    private boolean canBeCreated;
    private String message;

    public EventResponse(boolean canBeCreated, String message) {
        this.canBeCreated = canBeCreated;
        this.message = message;
    }

    // getters and setters
    public boolean isCanBeCreated() {
        return canBeCreated;
    }

    public void setCanBeCreated(boolean canBeCreated) {
        this.canBeCreated = canBeCreated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
