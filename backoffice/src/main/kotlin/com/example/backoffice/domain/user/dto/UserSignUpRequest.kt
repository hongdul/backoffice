package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.UserRole

data class UserSignUpRequest(
    val email: String,
    val password: String,
    val role: UserRole
)