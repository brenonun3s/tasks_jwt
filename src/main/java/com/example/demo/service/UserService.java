package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

 private final UserRepository userRepository;
 private final UserMapper userMapper;

 public UserResponseDTO register(UserRegisterDTO dto) {
  if (userRepository.existsByEmail(dto.email())) {
   throw new EntityExistsException("Email already in use");
  }

  User user = userMapper.toEntity(dto);
  user.setPassword(dto.password()); // Ainda sem encriptação

  User saved = userRepository.save(user);
  return userMapper.toResponseDto(saved);
 }

 public String login(UserLoginDTO dto) {
  User user = userRepository.findByEmail(dto.email())
    .orElseThrow(() -> new EntityNotFoundException("Invalid credentials"));

  if (!user.getPassword().equals(dto.password())) { // Corrigido: senha()
   throw new EntityNotFoundException("Invalid credentials");
  }

  return "Login successful for user: " + user.getName();
 }

 public String refreshToken(String tokenAntigo) {
  return "Token refreshed (simulação)";
 }
}
