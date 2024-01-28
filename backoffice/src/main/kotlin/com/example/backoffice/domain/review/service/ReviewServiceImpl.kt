package com.example.backoffice.domain.review.service

import com.example.backoffice.domain.exception.ModelNotFoundException
import com.example.backoffice.domain.menu.repository.MenuRepository
import com.example.backoffice.domain.review.dto.ReviewRequest
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.domain.review.repository.ReviewRepository
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewServiceImpl(
    val reviewRepository: ReviewRepository,
    val menuRepository: MenuRepository,
    val userRepository: UserRepository
) : ReviewService {

    @Transactional
    @PreAuthorize("hasRole('CUSTOMER')")
    override fun createReview(
        menuId: Long,
        reviewRequest: ReviewRequest, user: UserPrincipal
    ): ReviewDto {
        val menus = menuRepository.findByIdOrNull(menuId) ?: throw ModelNotFoundException("menu", menuId)
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val reviews = reviewRepository.save(reviewRequest.to(menus, users))
        return ReviewDto.from(reviews)
    }

    @Transactional
    @PreAuthorize("hasRole('CUSTOMER')")
    override fun updateReview(
        menuId: Long,
        reviewId: Long,
        reviewRequest: ReviewRequest,
        user: UserPrincipal
    ): ReviewDto {
        val reviews =
            reviewRepository.findByIdAndMenu_IdAndUser_Id(reviewId, menuId, user.id)
                ?: throw ModelNotFoundException("menuId, reviewId", reviewId)
        reviews.changeReview(reviewRequest)
        return ReviewDto.from(reviewRepository.save(reviews))
    }

    @Transactional
    @PreAuthorize("hasRole('CUSTOMER')")
    override fun deleteReview(menuId: Long, reviewId: Long, user: UserPrincipal) {
        val reviews =
            reviewRepository.findByIdAndMenu_IdAndUser_Id(reviewId, menuId, user.id)
                ?: throw ModelNotFoundException("menuId, reviewId", reviewId)
        reviewRepository.delete(reviews)
    }
}