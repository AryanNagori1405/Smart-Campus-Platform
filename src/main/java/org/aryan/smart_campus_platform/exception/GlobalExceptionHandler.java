package org.aryan.smart_campus_platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse(
                400, // 400 = validation error
                "Validation failed",
                errors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(
            StudentNotFoundException e) {

        // 404 = resource not found
        ErrorResponse response = new ErrorResponse(404, e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(SkillAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleSkillAlreadyExistsException(
            SkillAlreadyExistsException e) {

        // 409 = resource conflict
        ErrorResponse response = new ErrorResponse(409, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSkillNotFoundException(
            SkillNotFoundException e) {

        ErrorResponse response = new ErrorResponse(404, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SkillAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> handleSkillAlreadyAssignedException(
            SkillAlreadyAssignedException e) {

        ErrorResponse response = new ErrorResponse(409, e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
