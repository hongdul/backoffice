package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.menu.model.Menu
import jakarta.persistence.*

@Entity
@Table(name = "ordermap")
class OrderMap(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderhistory_id", nullable = false)
    val order: OrderHistory,

    @Column(name = "count", nullable = false)
    val count: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}