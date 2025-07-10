package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TokenRefreshRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
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
 @Operation(summary = "Registrar novo Usuário", description = "Usuário envia seu nome, email e senha para se registrar")
 @ApiResponses({
   @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
   @ApiResponse(responseCode = "400", description = "Campo obrigatório não preenchido"),
   @ApiResponse(responseCode = "409", description = "Email já em uso!")
 })
 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para registrar um novo usuário", required = true, content = @Content(schema = @Schema(implementation = UserRegisterDTO.class)))
 public UserResponseDTO register(@RequestBody @Valid UserRegisterDTO dto) {
  return userService.register(dto);
 }

 @PostMapping("/login")
 @Operation(summary = "Autenticar Usuário e Obter JWT", description = "Usuário envia seu email e senha registrados para se autenticar e obter o token JWT")
 @ApiResponses({
   @ApiResponse(responseCode = "200", description = "Token obtido"),
   @ApiResponse(responseCode = "400", description = "Dados Inválidos"),
   @ApiResponse(responseCode = "401", description = "Credenciais incorretas")
 })
 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Credenciais para autenticação", required = true, content = @Content(schema = @Schema(implementation = UserLoginDTO.class)))
 public TokenResponseDTO gerarToken(@RequestBody @Valid UserLoginDTO dto) {
  String token = userService.login(dto);
  return new TokenResponseDTO(token);
 }

 @Operation(summary = "Renovar o Token JWT", description = "Usuário envia seu token antigo e obtém um novo")
 @ApiResponses({
   @ApiResponse(responseCode = "200", description = "Token renovado"),
   @ApiResponse(responseCode = "401", description = "Token expirado ou inválido"),
   @ApiResponse(responseCode = "403", description = "Usuário não autenticado e/ou Token inválido"),
 })
 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Token antigo para renovação", required = true, content = @Content(schema = @Schema(implementation = TokenRefreshRequestDTO.class)))
 @PostMapping("/refresh")
 public TokenResponseDTO refreshToken(@RequestBody @Valid TokenRefreshRequestDTO dto) {
  String novoToken = userService.refreshToken(dto.tokenAntigo());
  return new TokenResponseDTO(novoToken);
 }
}
