package com.example.backoffice.domain.user.dto

data class UserSignUpRequest(
    val nickname: String,
    val email: String,
    val password: String,
)