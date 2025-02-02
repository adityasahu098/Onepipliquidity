package com.exml.Onepipliquidity1.model;


public class ResponseDTO1 {
    private int status;
    private String message;
    private Object data; // Can be used to send bonuses or any other data

    // Constructor with status and message
    public ResponseDTO1(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor with status, message, and data (used for bonuses or other objects)
    public ResponseDTO1(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
