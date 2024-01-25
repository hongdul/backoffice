package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
class OrderMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderhistory_id")
    val orderHistory: OrderHistory,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    val menu: Menu
)
