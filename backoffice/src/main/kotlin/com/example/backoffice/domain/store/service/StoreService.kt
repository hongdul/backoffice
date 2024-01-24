package com.example.backoffice.domain.store.service

import com.example.backoffice.domain.store.dto.CreateStoreArguments
import com.example.backoffice.domain.store.dto.StoreInfo
import com.example.backoffice.domain.store.dto.StoreResponse
import com.example.backoffice.domain.store.dto.UpdateStoreArguments
import com.example.backoffice.domain.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface StoreService {

    fun getPaginatedStores(pageable: Pageable) : Page<StoreResponse>

    fun getStoreById(storeId: Long) : StoreInfo

    fun createStore(createStoreArguments: CreateStoreArguments, seller: User): StoreResponse

    fun updateStore(storeId: Long, updateStoreArguments: UpdateStoreArguments): StoreResponse

    fun deleteStore(storeId: Long): String
}