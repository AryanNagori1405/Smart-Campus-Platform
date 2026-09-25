package org.aryan.smart_campus_platform.exception;

public class ApplicationAlreadyExistsException extends RuntimeException {
    public ApplicationAlreadyExistsException(String message) {
        super(message);
    }
}
