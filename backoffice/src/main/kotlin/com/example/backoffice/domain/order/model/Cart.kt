package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.user.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "cart")
class Cart(
    @ManyToOne
    @JoinColumn(name = "menuId", nullable = false)
    val menu: Menu,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column
    val count: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}