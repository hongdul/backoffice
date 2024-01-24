package com.example.backoffice.domain.store.dto

import com.example.backoffice.domain.store.model.Store
import com.example.backoffice.domain.store.model.StoreStatus
import com.example.backoffice.domain.user.model.User

data class CreateStoreArguments(
    val name: String,
    val phone: String,
    val address: String,
    val description: String
) {
    fun to(seller: User): Store {
        return Store(
            name = name,
            phone = phone,
            address = address,
            description = description,
            status = StoreStatus.OPEN,
            user = seller
        )
    }
}
