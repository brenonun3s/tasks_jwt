package com.example.demo.exceptions;

public class OperationException extends RuntimeException {

 public OperationException(String message, Exception e) {
  super(message);
 }

}
