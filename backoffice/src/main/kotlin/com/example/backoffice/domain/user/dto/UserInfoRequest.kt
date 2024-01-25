package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.QUser.user
import com.example.backoffice.domain.user.model.User
import com.example.backoffice.infra.security.UserPrincipal


import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

data class UserInfoRequest(
    val nickName: String,
    val address: String,
    val phoneNumber: String
) {
    fun to(user: User): Profile {
        return Profile(
            nickName = nickName,
            address = address,
            phoneNumber = phoneNumber,
            user = user
        )
    }
}
