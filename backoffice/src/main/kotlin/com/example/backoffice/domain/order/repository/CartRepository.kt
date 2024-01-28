package com.example.backoffice.domain.order.repository

import com.example.backoffice.domain.order.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
    fun findAllByUserId(userId: Long): List<Cart>?

    fun deleteAllByUserId(userId: Long)
}