package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(

    @Schema(description = "Nome do usuário", example = "Breno Nunes")
    @NotBlank(message = "Campo nome é obrigatório")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,

    @Schema(description = "Email do usuário", example = "breno@gmail.com")
    @NotBlank(message = "Campo Email é obrigatório")
    @Email(message = "Email deve ser válido")
    String email,

    @Schema(description = "Senha do usuário", example = "123456")
    @NotBlank(message = "Campo nome é obrigatório")
    @Size(min = 6, max = 100, message = "Sua senha deve ser entre 6 a 100 caracteres")
    String password

) {}
