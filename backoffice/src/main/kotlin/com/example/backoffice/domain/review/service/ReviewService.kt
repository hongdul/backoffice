package com.example.backoffice.domain.review.service

import com.example.backoffice.domain.review.dto.ReviewRequest
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.infra.security.UserPrincipal

interface ReviewService {

    fun createReview(menuId: Long, reviewRequest: ReviewRequest, user: UserPrincipal): ReviewDto
    fun updateReview(
        menuId: Long,
        reviewId: Long,
        reviewRequest: ReviewRequest,
        user: UserPrincipal
    ): ReviewDto

    fun deleteReview(menuId: Long, reviewId: Long, user: UserPrincipal)
}