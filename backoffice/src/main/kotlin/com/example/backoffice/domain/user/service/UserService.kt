package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.infra.security.UserPrincipal

interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserDto
    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse
    fun userInfo(user: UserPrincipal, userProfileRequest: UserProfileRequest): UserProfileResponse
}