package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDTO(

    @Schema(description = "ID do usuário", example = "1")
    Long id,

    @Schema(description = "Nome do usuário", example = "Breno Nunes")
    String name,

    @Schema(description = "Email do usuário", example = "breno@email.com")
    String email

) {}
