package com.example.backoffice.domain.review.dto

data class CreateReviewArguments(
    val rating: Int,
    val content: String,
    val menuId: Long,
)
