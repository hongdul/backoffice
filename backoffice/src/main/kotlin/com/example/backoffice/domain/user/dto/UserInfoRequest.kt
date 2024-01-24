package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.Profile


import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

data class UserInfoRequest(
    val nickName: String,
    val address: String,
    val phoneNumber: VarcharJdbcType
) {
    fun to(): Profile {
        return Profile(
            nickName = nickName,
            address = address,
            phoneNumber = phoneNumber
        )
    }
}
