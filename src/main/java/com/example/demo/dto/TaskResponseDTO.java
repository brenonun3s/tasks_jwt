package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record TaskResponseDTO(

    @Schema(description = "ID da tarefa", example = "1")
    Long id,

    @Schema(description = "Título da tarefa", example = "Finalizar relatório")
    String title,

    @Schema(description = "Descrição da tarefa", example = "Concluir e enviar o relatório mensal ao gerente")
    String description,

    @Schema(description = "Data de vencimento da tarefa", example = "2025-08-01")
    LocalDate dueDate,

    @Schema(description = "Status da tarefa (true = completa, false = pendente)", example = "false")
    Boolean completed,

    @Schema(description = "Usuário que criou a tarefa")
    UserResponseDTO user,

    @Schema(description = "Data e hora em que a tarefa foi criada", example = "2025-07-10T14:30:00")
    LocalDateTime createdAt

) {}

