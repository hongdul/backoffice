package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.LoginRequest
import com.example.backoffice.domain.user.dto.UserDto
import com.example.backoffice.domain.user.dto.UserSignUpRequest


interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserDto
    fun login(loginRequest: LoginRequest): UserDto
}