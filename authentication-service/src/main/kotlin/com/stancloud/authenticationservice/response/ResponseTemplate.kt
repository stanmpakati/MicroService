package com.stancloud.authenticationservice.response


class ResponseTemplate<T> {
  var status: String? = null
  var message: String? = null
  var data: T? = null
}
