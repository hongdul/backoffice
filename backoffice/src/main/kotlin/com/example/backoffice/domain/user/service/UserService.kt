package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.*
interface UserService {
    fun signUp(userSignUpRequest: UserSignUpRequest): UserDto
    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse
    fun createInfo(userInfoRequest: UserInfoRequest): ProfileDto
    fun updateInfo(profileId: Long, userInfoRequest: UserInfoRequest): ProfileDto
    fun getInfo(profileId: Long): ProfileDto
}