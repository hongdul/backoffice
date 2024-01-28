package com.example.backoffice.domain.store.dto

import com.example.backoffice.domain.store.model.Store
import com.example.backoffice.domain.store.model.StoreStatus


data class StoreResponse(
    val id: Long,
    val name: String,
    val phone: String,
    val address: String,
    val status: StoreStatus,
    val description: String
) {
    companion object {
        fun from(store: Store): StoreResponse {
            return StoreResponse(
                id = store.id!!,
                name = store.name,
                phone = store.phone,
                address = store.address,
                status = store.status,
                description = store.description,
            )
        }
    }
}