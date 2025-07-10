package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TokenJWTDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autorização de Usuários", description = "Operações relacionadas aos usuários e obtenção do token JWT")
public class AuthController {

 private final UserService userService;
 

 @PostMapping("/register")
 @Operation(summary = "Usuário se registrar", description = "Usuário envia seu nome, email e senha para se registrar e obter o token JWT")
 @ApiResponses({
  @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
  @ApiResponse(responseCode = "400", description = "Campo obrigatório não preenchido"),
  @ApiResponse(responseCode = "409", description = "Email já em uso!")
 })
 @io.swagger.v3.oas.annotations.parameters.RequestBody(
  description = "Dados para registrar um novo usuário",
  required = true,
  content = @Content(schema = @Schema(implementation = UserRegisterDTO.class))
 )
 public UserResponseDTO register(@RequestBody @Valid UserRegisterDTO dto) {
  return userService.register(dto);
 }

 @PostMapping("/login")
 public String gerarToken(@RequestBody @Valid UserLoginDTO dto) {
  return userService.login(dto);
 }

 @PostMapping("/refresh")
 public String refreshToken(@RequestBody TokenJWTDTO dto) {
  return userService.refreshToken(dto.tokenAntigo());
 }
}
