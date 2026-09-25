package org.aryan.smart_campus_platform.exception;

public class InvalidApplicationStatusTransitionException extends RuntimeException {
    public InvalidApplicationStatusTransitionException(String message) {
        super(message);
    }
}
