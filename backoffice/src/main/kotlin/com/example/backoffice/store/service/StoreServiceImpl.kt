package com.example.backoffice.store.service

import com.example.backoffice.store.dto.CreateStoreRequest
import com.example.backoffice.store.dto.StoreResponse
import com.example.backoffice.store.dto.UpdateStoreRequest
import org.springframework.stereotype.Service

@Service
class StoreServiceImpl : StoreService{
    override fun getAllStoreList(): List<StoreResponse> {
        TODO("Not yet implemented")
    }

    override fun getStoreById(storeId: Long): StoreResponse {
        TODO("Not yet implemented")
    }

    override fun createStore(request: CreateStoreRequest): StoreResponse {
        TODO("Not yet implemented")
    }

    override fun updateStore(storeId: Long, request: UpdateStoreRequest): StoreResponse {
        TODO("Not yet implemented")
    }

    override fun deleteStore(storeId: Long) {
        TODO("Not yet implemented")
    }
}