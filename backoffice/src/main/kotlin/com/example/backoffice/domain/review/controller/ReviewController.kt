package com.example.backoffice.domain.review.controller

import com.example.backoffice.domain.review.dto.CreateReviewArguments
import com.example.backoffice.domain.review.dto.ReviewDto
import com.example.backoffice.domain.review.service.ReviewService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/reviews")
@RestController
class ReviewController(
    val reviewService: ReviewService
) {

    @Operation(summary = "리뷰 생성")
    @PostMapping
    fun createReview(createReviewArguments: CreateReviewArguments,
                     authentication: Authentication
    ): ResponseEntity<ReviewDto> {
        val userPrincipal = authentication.principal as UserPrincipal
        val review = reviewService.createReview(createReviewArguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(review)
    }


}