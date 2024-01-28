package com.example.backoffice.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String,
)
