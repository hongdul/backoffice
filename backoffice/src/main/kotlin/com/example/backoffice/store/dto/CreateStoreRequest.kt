package com.example.backoffice.store.dto

data class CreateStoreRequest (
    val storename : String,
    val storecontent : String,
    val storenumber : Int,
)