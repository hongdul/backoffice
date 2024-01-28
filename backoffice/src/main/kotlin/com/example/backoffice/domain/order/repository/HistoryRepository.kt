package com.example.backoffice.domain.order.repository

import com.example.backoffice.domain.order.model.History
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryRepository : JpaRepository<History, Long> {

    fun findAllByUserId(userId: Long): List<History>?
}