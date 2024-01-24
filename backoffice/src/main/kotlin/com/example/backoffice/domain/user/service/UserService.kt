package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.*
interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserDto
    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse
}