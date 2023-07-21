package com.stancloud.authenticationservice

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/health")
class HealthController {
  @RequestMapping("/ping")
  fun ping(): String {
    return "pong"
  }
}