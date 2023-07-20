package com.stancloud.authenticationservice.auth

import com.stancloud.authenticationservice.dto.LoginRequest
import com.stancloud.authenticationservice.dto.LoginResponse
import com.stancloud.authenticationservice.dto.SignupRequest
import com.stancloud.authenticationservice.dto.SignupResponse
import com.stancloud.authenticationservice.response.ResponseHandler
import com.stancloud.authenticationservice.response.ResponseTemplate
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class authController(
  private val authService: AuthService,
) {

  @PostMapping("/signup")
  fun signup(
    @Valid @RequestBody request: SignupRequest,
  ): SignupResponse {
    return authService.signup(request)
  }

  @PostMapping("/login")
  fun login(
    @RequestBody request: LoginRequest,
  ): ResponseTemplate<String> {
    var response: LoginResponse = authService.login(request)
    return ResponseHandler.generateOkResponse(response.token)
  }

//  @PostMapping("/refresh")
//  fun refresh(
//    @RequestBody request: RefreshRequest,
//  ): RefreshResponse {
//    return authService.refresh(request)
//  }
}