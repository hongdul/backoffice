package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.user.model.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Cart(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "menuId", nullable = false)
    val menu: Menu,

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    val user: User
) {

}