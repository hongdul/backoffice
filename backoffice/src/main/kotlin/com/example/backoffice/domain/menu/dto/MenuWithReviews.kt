package com.example.backoffice.domain.menu.dto

import com.example.backoffice.domain.menu.model.MenuStatus

data class MenuWithReviews(
    val id: Long?,
    val storeId: Long?,
    val name: String,
    val description: String,
    val price: Long,
    val status: MenuStatus,
//    val reviews: List<ReviewDto>
)
