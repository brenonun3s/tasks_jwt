package com.example.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

 @Value("${jwt.secret}")
 private String SECRET_KEY;

 private final long EXPIRATION_TIME = 3600000;

 public String generateToken(String username) {
  return Jwts.builder()
    .setSubject(username)
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
    .compact();
 }

 public String extractUsername(String token) {
  return Jwts.parser()
    .setSigningKey(SECRET_KEY)
    .parseClaimsJws(token)
    .getBody()
    .getSubject();
 }

 public boolean validateToken(String token) {
  try {
   Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
   return true;
  } catch (Exception e) {
   return false;
  }
 }

}
