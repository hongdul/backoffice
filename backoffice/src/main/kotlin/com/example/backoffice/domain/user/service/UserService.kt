package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.infra.security.UserPrincipal

interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserDto
    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse
    fun createInfo(userInfoRequest: UserInfoRequest, user: UserPrincipal): ProfileDto
    fun updateInfo(profileId: Long, userInfoRequest: UserInfoRequest, user: UserPrincipal): ProfileDto
    fun getInfo(userId: Long): ProfileDto
}