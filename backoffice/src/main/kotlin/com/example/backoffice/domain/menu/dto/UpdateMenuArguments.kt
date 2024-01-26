package com.example.backoffice.domain.menu.dto


data class UpdateMenuArguments(
    val name: String,
    val description: String,
    val price: Long,
)