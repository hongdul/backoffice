package com.example.backoffice.domain.menu.dto

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.menu.model.MenuStatus
import com.example.backoffice.domain.review.dto.ReviewDto

data class MenuWithReviews(
    val id: Long?,
    val storeId: Long?,
    val name: String,
    val description: String,
    val price: Long,
    val status: MenuStatus,
    val reviews: List<ReviewDto>
) {
    companion object {
        fun infoFrom(menu: Menu): MenuWithReviews {
            return MenuWithReviews(
                id = menu.id!!,
                storeId = menu.store.id!!,
                name = menu.name,
                description = menu.description,
                price = menu.price,
                status = menu.status,
                reviews = menu.reviews.map{ ReviewDto.from(it) }
            )
        }
    }
}
