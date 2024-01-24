package com.rating.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
    public ResourceNotFoundException() {
        super("Rsource Not found please correct it");
    }
}
