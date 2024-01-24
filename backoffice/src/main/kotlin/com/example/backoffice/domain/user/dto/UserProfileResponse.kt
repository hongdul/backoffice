package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

data class UserProfileResponse(
    val nickname: String,
    val email: String,
    val password: String = "*******",
    val address: String,
    val phoneNumber: VarcharJdbcType,
    val role: UserRole
) {
    companion object {
        fun from(user: User, profile: Profile): UserProfileResponse {
            return UserProfileResponse(
                nickname = profile.nickName,
                email = user.email,
                password = "********",
                address = profile.address,
                phoneNumber = profile.phoneNumber,
                role = user.role
            )
        }
    }
}