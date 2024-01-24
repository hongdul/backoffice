package com.example.backoffice.domain.review.service

import com.example.backoffice.domain.review.dto.CreateReviewArguments
import com.example.backoffice.domain.review.dto.DeleteReviewArguments
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.domain.review.dto.UpdateReviewArguments
import com.example.backoffice.domain.user.model.User

interface ReviewService {

    fun createReview(createReviewArguments: CreateReviewArguments, user: User): ReviewDto
    fun updateReview(updateReviewArguments: UpdateReviewArguments, user: User): ReviewDto
    fun deleteReview(deleteReviewArguments: DeleteReviewArguments, user: User)
}