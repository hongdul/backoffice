package com.example.backoffice.domain.order.repository

import com.example.backoffice.domain.order.model.OrderMap
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMapRepository : JpaRepository<OrderMap, Long> {
    fun findAllByHistoryId(historyId: Long): List<OrderMap>?
}