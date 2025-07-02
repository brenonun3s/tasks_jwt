package com.example.demo.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
    List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> {
      Map<String, String> err = new HashMap<>();
      err.put("field", error.getField());
      err.put("message", error.getDefaultMessage());
      return err;
    }).collect(Collectors.toList());

    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("timestamp", LocalDateTime.now());
    response.put("errors", errors);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OperationException.class)
  public ResponseEntity<Object> handleOperacao(OperationException ex) {
      Map<String, Object> response = new HashMap<>();
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("timestamp", LocalDateTime.now());
      response.put("message", "Internal error: " + ex.getMessage());
  
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
