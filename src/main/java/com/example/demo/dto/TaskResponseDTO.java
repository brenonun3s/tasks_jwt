package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
  Long id,
  String title,
  String description,
  LocalDate dueDate,
  Boolean completed,
  LocalDateTime createdAt ) {

}
