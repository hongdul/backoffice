package com.example.backoffice.store.service

import com.example.backoffice.store.dto.CreateStoreRequest
import com.example.backoffice.store.dto.StoreResponse
import com.example.backoffice.store.dto.UpdateStoreRequest


interface StoreService {

    fun getAllStoreList() : List<StoreResponse>

    fun getStoreById(storeId: Long) : StoreResponse

    fun createStore(request : CreateStoreRequest) : StoreResponse

    fun updateStore(storeId : Long, request: UpdateStoreRequest) : StoreResponse

    fun deleteStore(storeId: Long)

}
