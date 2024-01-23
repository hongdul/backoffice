package com.example.backoffice.exception.dto

data class StoreNotFoundException(val storeName : String, val id : Long) : RuntimeException(
    " Store $storeName not found with given id : $id"
)
