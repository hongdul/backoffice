package com.example.backoffice.domain.order.service

import com.example.backoffice.domain.menu.dto.MenuArguments
import com.example.backoffice.domain.order.dto.CartDto
import com.example.backoffice.domain.order.dto.CartRequest
import com.example.backoffice.domain.order.dto.OrderMapDto
import com.example.backoffice.infra.security.UserPrincipal

interface OrderService {
    fun menuInCart(menuId: Long, user: UserPrincipal, cartRequest: CartRequest): CartDto
    fun getCart(user: UserPrincipal): List<CartDto>
    fun deleteCart(user: UserPrincipal): String
    fun order(user: UserPrincipal)
    fun getHistory(user: UserPrincipal): List<List<OrderMapDto>>
}