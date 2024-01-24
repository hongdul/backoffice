package com.example.backoffice.domain.exception

class StoreNotFoundException (val storeId: Long?):
RuntimeException("Store not found with given storeId: $storeId")