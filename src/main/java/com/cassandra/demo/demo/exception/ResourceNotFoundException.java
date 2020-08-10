package com.cassandra.demo.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final Long serialVersionUUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
