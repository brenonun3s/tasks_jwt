package com.example.demo.exception;

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
      err.put("Campo", error.getField());
      err.put("Mensagem", error.getDefaultMessage());
      return err;
    }).collect(Collectors.toList());

    Map<String, Object> response = new HashMap<>();
    response.put("Status", HttpStatus.BAD_REQUEST.value());
    response.put("Timestamp", LocalDateTime.now());
    response.put("Erros:", errors);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("Status", HttpStatus.NOT_FOUND.value());
    response.put("Timestamp", LocalDateTime.now());
    response.put("Mensagem", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OperationException.class)
  public ResponseEntity<Object> handleOperacao(OperationException ex) {
      Map<String, Object> response = new HashMap<>();
      response.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("Timestamp", LocalDateTime.now());
      response.put("Mensagem", "Internal error: " + ex.getMessage());
  
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
      return ResponseEntity
          .status(HttpStatus.CONFLICT)
          .body(ex.getMessage());
  }

  

}
