package com.example.backoffice.domain.review.dto

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.review.model.Review
import com.example.backoffice.domain.user.model.User

data class ReviewRequest(
    val rating: Int,
    val content: String
) {
    fun to(menus: Menu, users: User): Review {
        return Review(
            rating = rating,
            content = content,
            menu = menus,
            user = users
        )
    }
}
