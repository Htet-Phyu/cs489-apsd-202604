package com.apsd.studentAPI.exception;

//A custom exception: used when data not found
/*
instead of throw new RuntimeException("error");
    👉 More meaningful
    👉 Cleaner code
    👉 Better for API responses
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
