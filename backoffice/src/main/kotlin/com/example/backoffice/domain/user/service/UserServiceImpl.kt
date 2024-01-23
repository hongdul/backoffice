package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.user.dto.UserDto
import com.example.backoffice.domain.user.dto.UserSignUpRequest

class UserServiceImpl(): UserService {
    override fun signUp(userSignUpRequest: UserSignUpRequest): UserDto {
        TODO(" 클라이언트로 유저정보 받고 DB에 저장후 dto로 변환 후 반환")
    }

    override fun login() {
        TODO("Not yet implemented")
    }
}