package com.example.backoffice.domain.review.dto

import com.example.backoffice.domain.review.model.Review

data class ReviewDto(
    val id: Long,
    val userName: String,
    val rating: Int,
    val content: String,
    val userId: Long
) {
    companion object {
        fun from(review: Review): ReviewDto {
            return ReviewDto(
                id = review.id!!,
                userName = review.user.nickname,
                rating = review.rating,
                content = review.content,
                userId = review.user.id!!
            )
        }
    }
}
