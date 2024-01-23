package com.example.backoffice.domain.menu.dto

import com.example.backoffice.domain.menu.model.MenuStatus

data class MenuResponse(
    val id: Long?,
    val name: String,
    val description: String,
    val storeId: Long?,
    val status: MenuStatus,
    val price: Long,
)