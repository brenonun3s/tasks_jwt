package com.example.demo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;

  public UserResponseDTO register(UserRegisterDTO dto) {
    if (userRepository.existsByEmail(dto.email())) {
      throw new EntityExistsException("Email already in use");
    }

    User user = userMapper.toEntity(dto);
    user.setPassword(encoder.encode(dto.password())); 

    User saved = userRepository.save(user);
    return userMapper.toResponseDto(saved);
  }

  public String login(UserLoginDTO dto) {
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
    );

    if (auth.isAuthenticated()) {
      return jwtUtil.generateToken(dto.email());
    }

    throw new RuntimeException("Invalid credentials");
  }

  public String refreshToken(String tokenAntigo) {
    String email = jwtUtil.extractUsername(tokenAntigo);
    return jwtUtil.generateToken(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .roles("USER")
        .build();
  }
}
