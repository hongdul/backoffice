package com.example.backoffice.domain.review.dto

data class UpdateReviewArguments(
    val id: Long?,
    val rating: Int,
    val content: String
)
