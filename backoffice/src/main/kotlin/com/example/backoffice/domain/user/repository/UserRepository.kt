package com.example.backoffice.domain.user.repository

import com.example.backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean // 이메일 중복 검사
    fun findByEmail(email: String): User?

}