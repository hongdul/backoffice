package com.example.backoffice.domain.review.service

import com.example.backoffice.domain.review.dto.CreateReviewArguments
import com.example.backoffice.domain.review.dto.DeleteReviewArguments
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.domain.review.dto.UpdateReviewArguments
import com.example.backoffice.domain.review.repository.ReviewRepository
import com.example.backoffice.domain.user.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewServiceImpl(
    val reviewRepository: ReviewRepository,
    val menuRepository: ReviewRepository
): ReviewService {

    @Transactional
    override fun createReview(createReviewArguments: CreateReviewArguments, user: User): ReviewDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateReview(updateReviewArguments: UpdateReviewArguments, user: User): ReviewDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteReview(deleteReviewArguments: DeleteReviewArguments, user: User) {
        TODO("Not yet implemented")
    }
}