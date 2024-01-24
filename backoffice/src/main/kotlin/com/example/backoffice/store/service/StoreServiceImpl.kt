package com.example.backoffice.store.service

import com.example.backoffice.exception.dto.StoreNotFoundException
import com.example.backoffice.store.dto.CreateStoreRequest
import com.example.backoffice.store.dto.StoreResponse
import com.example.backoffice.store.dto.UpdateStoreRequest
import com.example.backoffice.store.model.Store
import com.example.backoffice.store.model.toResponse
import com.example.backoffice.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    private val storeRepository: StoreRepository
) : StoreService {
    override fun getAllStoreList(): List<StoreResponse> {
        return storeRepository.findAll().map { it.toResponse() }
    }

    override fun getStoreById(
        storeId: Long
    ): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId)
            ?: throw StoreNotFoundException("Store", storeId)
        return store.toResponse()
    }

    @Transactional
    override fun createStore(request: CreateStoreRequest): StoreResponse {

        return storeRepository.save(
            Store(
                name = request.name,
                address = request.address,
                phone = request.phone,
                status = request.status,
                description = request.description
            )
        ).toResponse()
    }

    @Transactional
    override fun updateStore(storeId: Long, request: UpdateStoreRequest): StoreResponse {
        val store = storeRepository.findByIdOrNull(storeId)
            ?: throw StoreNotFoundException("Store", storeId)
        val (name, address, phone, status, id, description) = request
        store.name = name
        store.address = address
        store.phone = phone
        store.status = status
        store.id = id
        store.description = description

        return storeRepository.save(store).toResponse()
    }

    @Transactional
    override fun deleteStore(storeId: Long) {
        val store = storeRepository.findByIdOrNull(storeId)
            ?: throw StoreNotFoundException("Store", storeId)
        storeRepository.delete(store)
    }
}