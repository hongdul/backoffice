package com.example.backoffice.domain.store.dto

data class UpdateStoreArguments(
    val name: String,
    val phone: String,
    val address: String,
    val description: String,
)
