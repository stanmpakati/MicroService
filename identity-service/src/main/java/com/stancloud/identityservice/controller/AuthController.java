package com.stancloud.identityservice.controller;

import com.stancloud.identityservice.dto.UserDto;
import com.stancloud.identityservice.dto.UserResponseDto;
import com.stancloud.identityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public UserResponseDto register(@RequestBody UserDto userDto) {
    return authService.saveUser(userDto);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserDto userDto) {
    return authService.login(userDto);
  }

  @GetMapping("/validate/{token}")
  public String validateToken(@RequestParam String token) {
    authService.validateToken(token);
    return "Token is valid";
  }



}
