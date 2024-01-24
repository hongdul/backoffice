package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole

data class UserDto(
    val email: String,
    val role: UserRole
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                email = user.email,
                role = UserRole.MEMBER
            )
        }
    }

}