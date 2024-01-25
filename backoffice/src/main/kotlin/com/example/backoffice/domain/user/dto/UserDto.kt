package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole

data class UserDto(
    val id: Long?,
    val email: String,
    val role: UserRole,
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                email = user.email,
                role = user.role
            )
        }
    }

}