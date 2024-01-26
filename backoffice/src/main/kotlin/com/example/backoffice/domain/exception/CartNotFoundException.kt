package com.example.backoffice.domain.exception

data class CartNotFoundException(private val cartId: Long?):
    RuntimeException("cart not found with given cartId: $cartId")