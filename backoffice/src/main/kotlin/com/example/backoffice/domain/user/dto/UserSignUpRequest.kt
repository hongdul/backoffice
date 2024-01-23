package com.example.backoffice.domain.user.dto

data class UserSignUpRequest(
    val nickName: String,
    val email: String,
    val password: String,
    val role: String
)