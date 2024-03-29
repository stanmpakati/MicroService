package com.stancloud.authenticationservice.dto

import com.stancloud.authenticationservice.model.User
import java.time.LocalDateTime

data class SignupResponse(
  val id: Long,
  val firstName: String,
  val lastName: String,
  val email: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
) {
  constructor(user: User) : this(
    user.id!!,
    user.firstName,
    user.lastName,
    user.email,
    user.createdAt,
    user.updatedAt,
  )
}

