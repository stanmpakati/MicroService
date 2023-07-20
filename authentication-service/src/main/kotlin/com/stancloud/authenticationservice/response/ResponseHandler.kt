package com.stancloud.authenticationservice.response

import org.springframework.http.HttpStatus

object ResponseHandler {
  fun <T> generateResponse(message: String?, status: HttpStatus, responseObj: T): ResponseTemplate<T> {
    val res = ResponseTemplate<T>()
    res.status = status.value().toString()
    res.message = message
    res.data = responseObj
    return res
  }

  fun <T> generateOkResponse(responseObj: T): ResponseTemplate<T> {
    val res = ResponseTemplate<T>()
    res.status = "200"
    res.message = "Success"
    res.data = responseObj
    return res
  }

//  fun generateErrorResponse(responseObj: CustomExceptionTemplate): ResponseEntity<CustomExceptionTemplate?> {
//    val status = HttpStatus.OK
//    val map: MutableMap<String, Any> = HashMap()
//    map["message"] = "Success"
//    map["status"] = status.value()
//    map["data"] = responseObj
//    return ResponseEntity<Any?>(map, status)
//  }
}