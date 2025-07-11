package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.JwtUtil;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;

  public UserResponseDTO register(UserRegisterDTO dto) {
    if (userRepository.existsByEmail(dto.email())) {
      throw new EmailAlreadyExistsException("Email já está em uso!");
    }

    User user = userMapper.toEntity(dto);
    user.setPassword(encoder.encode(dto.password()));

    User saved = userRepository.save(user);
    return userMapper.toResponseDto(saved);
  }

  public String login(UserLoginDTO dto) {
    UserDetails userDetails = loadUserByUsername(dto.email());

    if (encoder.matches(dto.password(), userDetails.getPassword())) {
      return jwtUtil.generateToken(dto.email());

    }
    throw new RuntimeException("Credenciais Inválidas");
  }

  public String refreshToken(String tokenAntigo) {
    String email = jwtUtil.extractUsername(tokenAntigo);
    return jwtUtil.generateToken(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado/ localizado!"));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
