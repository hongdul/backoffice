package com.example.backoffice.domain.store.dto

import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.store.model.Store
import com.example.backoffice.domain.store.model.StoreStatus

data class StoreInfo (
    val id: Long,
    val name: String,
    val description: String,
    val phone: String,
    val address: String,
    val status: StoreStatus,
    val menus: List<MenuResponse>
) {
    companion object {
        fun from(store: Store): StoreInfo {
            return StoreInfo(
                id = store.id!!,
                name = store.name,
                phone = store.phone,
                address = store.address,
                status = store.status,
                description = store.description,
                menus = store.menus.map { MenuResponse.from(it) }
            )
        }
    }
}

