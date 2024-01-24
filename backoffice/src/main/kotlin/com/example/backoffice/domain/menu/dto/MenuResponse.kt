package com.example.backoffice.domain.menu.dto

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.menu.model.MenuStatus

data class MenuResponse(
    val id: Long?,
    val storeId: Long?,
    val name: String,
    val description: String,
    val price: Long,
    val status: MenuStatus,
) {
    companion object {
        fun from(menu: Menu): MenuResponse {
            return MenuResponse(
                id = menu.id!!,
                storeId = menu.store.id!!,
                name = menu.name,
                description = menu.description,
                price = menu.price,
                status = menu.status
            )
        }
    }
}