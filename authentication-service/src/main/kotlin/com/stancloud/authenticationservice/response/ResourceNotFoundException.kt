package com.stancloud.authenticationservice.response;

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException : RuntimeException {
  constructor() : super("Resource not found")
  constructor(message: String?) : super(message)
}
