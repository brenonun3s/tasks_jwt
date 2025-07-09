package com.example.demo.exception;

public class OperationException extends RuntimeException {

 public OperationException(String message, Exception e) {
  super(message);
 }

}
