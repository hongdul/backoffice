package com.example.backoffice.domain.review.repository

import com.example.backoffice.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, Long> {
    fun findByIdAndMenu_IdAndUser_Id(reviewId: Long, menuId: Long, userId: Long): Review?
}