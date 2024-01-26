package com.example.backoffice.domain.order.repository

import com.example.backoffice.domain.order.model.OrderHistory
import org.springframework.data.jpa.repository.JpaRepository

interface OrderHistoryRepository : JpaRepository<OrderHistory, Long> {

    fun findAllByUserId(userId: Long): List<Long>?
}