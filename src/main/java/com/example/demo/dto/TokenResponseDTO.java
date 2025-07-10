package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenResponseDTO(

    @Schema(description = "Token JWT gerado para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String token

) {}

