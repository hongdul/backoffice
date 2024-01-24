package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole

data class UserDto(
    val id: Long?,
    val nickname: String,
    val email: String,
    val role: UserRole,
    val token: String? = null,
) {
    companion object {
        fun from(user: User, token: String?): UserDto {
            return UserDto(
                id = user.id,
                nickname = user.nickname,
                email = user.email,
                role = user.role
            )
        }
    }

}