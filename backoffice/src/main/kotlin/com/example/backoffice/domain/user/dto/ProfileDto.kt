package com.example.backoffice.domain.user.dto

import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.QProfile.profile
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

data class ProfileDto(
    val nickName: String,
    val address: String,
    val phoneNumber: VarcharJdbcType
) {
    companion object {
        fun from(profile: Profile): ProfileDto {
            return ProfileDto(
                nickName = profile.nickName,
                address = profile.address,
                phoneNumber = profile.phoneNumber
            )
        }
    }
}
