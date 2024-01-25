package com.example.backoffice.infra.security

import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val email: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, email: String, roles: Set<String>) : this(
        id,
        email,
        roles.map { SimpleGrantedAuthority("ROLE_$it") })

    fun to(): User {
        return User(
            id = id,
            email = email,
            password = "",
            role = UserRole.CUSTOMER
        )
    }
}