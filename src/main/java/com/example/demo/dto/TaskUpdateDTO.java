package com.example.demo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskUpdateDTO(

  @NotBlank(message = "Title is required") @Size(min = 5, max = 50, message = "The title must be between 5 and 50 characters long.") String title,

  @NotBlank(message = "Description is required") @Size(min = 5, max = 150, message = "The description must be between 5 and 150 characters long.") String description,

  @FutureOrPresent(message = "Due date must be today or in the future") LocalDate dueDate,

  @NotNull(message = "Completed field is required") Boolean completed

) {
}
