package com.example.backoffice.store.dto

data class UpdateStoreRequest (
    val storename : String,
    val storecontent : String,
    val storenumber : Int,
)