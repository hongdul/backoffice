package com.example.backoffice.domain.user.repository

import com.example.backoffice.domain.user.model.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {
}