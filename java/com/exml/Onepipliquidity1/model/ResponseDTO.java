package com.exml.Onepipliquidity1.model;

public class ResponseDTO {
	
	   private int statusCode;
	    private String message;
	    private Integer userId;  // Change to Integer to allow null value
        
	    // Constructor for success response
	    public ResponseDTO(int statusCode, String message) {
	        this.statusCode = statusCode;
	        this.message = message;
	    }

	    // Constructor for success response with userId
	    public ResponseDTO(int statusCode, String message, Integer userId) {
	        this.statusCode = statusCode;
	        this.message = message;
	        this.userId = userId;
	    }

	    // Getters and Setters
	    public int getStatusCode() {
	        return statusCode;
	    }

	    public void setStatusCode(int statusCode) {
	        this.statusCode = statusCode;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }
	    
	    
    
}

