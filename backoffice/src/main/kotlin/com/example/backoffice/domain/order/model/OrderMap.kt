package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
class OrderMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val orderHistory: OrderHistory,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User
)
