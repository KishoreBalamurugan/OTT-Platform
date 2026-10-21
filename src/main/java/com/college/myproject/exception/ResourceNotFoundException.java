package com.college.myproject.exception;

/**
 * Custom exception thrown when a requested resource (User, Movie, Watchlist item) is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}