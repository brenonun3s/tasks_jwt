package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TokenJWTDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

 private final UserService userService;
 
 @PostMapping("/register")
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
