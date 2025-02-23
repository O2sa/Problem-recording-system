package com.example.problem_register.base;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ApiResponse<String>>
    // handleValidationException(MethodArgumentNotValidException ex) {
    // String errorMessage =
    // ex.getBindingResult().getFieldError().getDefaultMessage();
    // ApiResponse<String> response = new ApiResponse<>(false, errorMessage, null);
    // return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(NotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle uncaught exceptions
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
    //     System.err.println(ex);
    //     return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMsg = error.getDefaultMessage();
            errors.put(fieldName, errorMsg);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Utility method to build error responses
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", message);
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        return new ResponseEntity<>(response, status);
    }
}
