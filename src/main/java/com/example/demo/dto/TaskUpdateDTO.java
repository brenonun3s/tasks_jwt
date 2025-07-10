package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskUpdateDTO(

   @Schema(description = "Titulo da tarefa", example = "Estudar")
   @NotBlank(message = "Campo Titulo da Tarefa é obrigatório")
   @Size(min = 5, max = 50, message = "O titulo deve ser entre 5 (mínimo) a 50 (máximo) de caracteres")
   String title,

   @Schema(description = "Descrição da Tarefa", example = "Estudar para a prova de Java")
   @NotBlank(message = "Campo Descrição da Tarefa é obrigatório")
   @Size(min = 5, max = 150, message = "A descrição deve ser entre 5 (mínimo) a 150 (máximo) de caracteres")
   String description,

  @Schema(description = "Data de Vencimento da tarefa", example = "07/09/2025")
  @JsonFormat(pattern = "dd-MM-yyyy")
  @FutureOrPresent(message = "Campo Data de Vencimento aceita apenas data do presente ou futuro, datas passadas são inválidas")
  LocalDate dueDate,

  @Schema(description = "Indica se a tarefa foi completada", examples = {"true", "false"})
  @NotNull(message = "Campo Completada é obrigatório") 
  Boolean completed

) {
}
