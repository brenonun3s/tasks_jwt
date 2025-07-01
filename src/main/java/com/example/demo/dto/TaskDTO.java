package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskDTO(
 @NotBlank(message = "Field Title of Task is required")
 @Size(min = 5, max = 50, message = "The title must be between 5 and 50 characters long.")
 String title, 
 
 @NotBlank(message = "Field Description of Task is required")
 @Size(min = 5, max = 150, message = "The description must be between 5 and 150 characters long.")
 String description, 
 
 @FutureOrPresent(message = "Field Due Date only accepts data in the present or future")
 @NotNull(message = "Due date is required")
 @JsonFormat(pattern = "dd-MM-yyyy")
 LocalDate dueDate) {
 
}
