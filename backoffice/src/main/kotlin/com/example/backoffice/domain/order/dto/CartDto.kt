package com.example.backoffice.domain.order.dto

import com.example.backoffice.domain.order.model.Cart


data class CartDto(
    val userId: Long,
    val count: Int,
    val menu: String
) {
    companion object {
        fun from(cart: Cart): CartDto {
            return CartDto(
                userId = cart.user.id!!,
                count = cart.count,
                menu = cart.menu.name
            )
        }

    }

}