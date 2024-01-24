package com.example.backoffice.domain.exception

class StoreNotFoundException (private val storeId: Long?):
    RuntimeException("Store not found with given storeId: $storeId")