package com.stancloud.identityservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
  @Value("${application.security.jwt.secret-key}")
  private static String secretKey;

  @Value("${application.security.jwt.expiration}")
  private static Long jwtExpiration;

  public Claims validateToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean isTokenExpired(String token) {
    return validateToken(token)
      .getExpiration()
      .before(new Date(System.currentTimeMillis()));
  }

  public boolean isTokenValid(String token) {
    return !isTokenExpired(token);
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  public String generateToken(String subject) {
    return createToken(subject, new HashMap<>());
  }

  private String createToken(String subject, Map<String, Object> claims) {
    return Jwts.builder()
      .setSubject(subject)
      .setClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
      .signWith(getSignKey(), getSigningAlgorithm())
      .compact();
  }

  private Key getSignKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  private SignatureAlgorithm getSigningAlgorithm() {
    return SignatureAlgorithm.HS256;
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = validateToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
