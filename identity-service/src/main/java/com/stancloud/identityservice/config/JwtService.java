package com.stancloud.identityservice.config;

import com.stancloud.identityservice.entity.UserCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class JwtService {
//  todo: move to application.properties
//  @Value("${application.security.jwt.secret}")
  private String secretKey = "gHrUHrU1FZGytdX/sqRTmTOc/8VfZa573Ri/0UvucDM=";

//  @Value("${application.security.jwt.expiration}")
  private Long jwtExpiration = 8640000000000L; // 24 hours


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

  public boolean isTokenValid(String token, UserDetails userDetails) {
    String email = extractUsername(token);
    return (email == userDetails.getUsername()) && !isTokenExpired(token);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String generateToken(UserCredential user) {
    Claims claims = Jwts.claims().setSubject(user.getId().toString());

    List<String> auth = new LinkedList<>();
    user.getUserRoles().forEach(role -> auth.add(role.toString()));
    claims.put("auth", auth);

    return createToken(user.getEmail(), claims);
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
