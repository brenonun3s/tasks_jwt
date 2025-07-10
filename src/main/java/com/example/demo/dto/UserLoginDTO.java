package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(

  @Schema(description = "Email do usuário", example="breno@gmail.com")
  @NotBlank(message = "Campo Email é obrigatório") @Email(message = "Campo email deve estar no formato válido") String email,

  @Schema(description = "Senha do Usuário", example = "123456")
  @NotBlank(message = "Campo Senha é obrigatório") String password

) {
}
