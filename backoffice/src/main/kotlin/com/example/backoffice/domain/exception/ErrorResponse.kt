package com.example.backoffice.domain.exception

data class ErrorResponse(
    val message: String?,
    val errorCode: String
)