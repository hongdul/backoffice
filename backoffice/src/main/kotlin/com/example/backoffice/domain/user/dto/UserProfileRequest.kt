package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.User
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

data class UserProfileRequest(
    val nickName: String,
    val address: String,
    val phoneNumber: VarcharJdbcType
)