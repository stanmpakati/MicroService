package com.stancloud.identityservice.service;

import com.stancloud.identityservice.config.JwtService;
import com.stancloud.identityservice.dto.UserDto;
import com.stancloud.identityservice.dto.UserResponseDto;
import com.stancloud.identityservice.entity.UserCredential;
import com.stancloud.identityservice.repo.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  private UserCredentialRepo userRepo;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  public UserResponseDto saveUser(UserDto userDto) {
    UserCredential user = UserCredential.builder()
      .email(userDto.getEmail())
      .password(passwordEncoder.encode(userDto.getPassword()))
      .userRoles(userDto.getUserRoles())
      .build();

    UserCredential savedUser = userRepo.save(user);

    return UserResponseDto.builder()
      .id(savedUser.getId())
      .email(savedUser.getEmail())
      .userRoles(savedUser.getUserRoles())
      .build();
  }

  public void validateToken(String token) {
    jwtService.validateToken(token);
  }

  public String login(UserDto userDto) {
    UserCredential user = userRepo.findByEmail(userDto.getEmail())
      .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
      String token = jwtService.generateToken(user);
      return token;
    } else {
      throw new RuntimeException("Username Password incorrect");
    }
//    authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
//    );
//
//    UserCredential user = userRepo.findByEmail(userDto.getEmail())
//        .orElseThrow(() -> new RuntimeException("User not found"));
//
//    return jwtService.generateToken(user.getEmail());
  }
}
