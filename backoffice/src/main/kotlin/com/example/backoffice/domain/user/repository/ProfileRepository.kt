package com.example.backoffice.domain.user.repository

import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {
    fun findByIdAndUser(profileId: Long, user: User): Profile?
    fun findByUser(user: User): Profile?
    fun findByUserId(userId: Long): Profile?
}