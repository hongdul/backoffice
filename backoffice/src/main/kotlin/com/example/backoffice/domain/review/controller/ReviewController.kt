package com.example.backoffice.domain.review.controller

import com.example.backoffice.domain.review.dto.ReviewRequest
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.domain.review.service.ReviewService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/menus/{menuId}/reviews")
@RestController
class ReviewController(
    val reviewService: ReviewService
) {

    @Operation(summary = "리뷰 생성")
    @PostMapping
    fun createReview(
        @PathVariable menuId: Long,
        @RequestBody reviewRequest: ReviewRequest,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<ReviewDto> {
        val review = reviewService.createReview(menuId, reviewRequest, user)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(review)
    }

    @Operation(summary = "리뷰 수정")
    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable menuId: Long,
        @PathVariable reviewId: Long,
        @RequestBody reviewRequest: ReviewRequest,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<ReviewDto> {
        val review = reviewService.updateReview(menuId, reviewId, reviewRequest, user)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(review)
    }

    @Operation(summary = "리뷰 삭제")
    @PostMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable menuId: Long,
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<ReviewDto> {
        reviewService.deleteReview(menuId, reviewId, user)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }



}