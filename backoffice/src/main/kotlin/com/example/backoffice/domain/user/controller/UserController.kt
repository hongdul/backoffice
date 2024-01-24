package com.example.backoffice.domain.user.controller

import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.domain.user.service.UserService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @Operation(summary = "회원가입")
    @PostMapping("/sign")
    fun signup(@RequestBody userSignUpRequest: UserSignUpRequest): ResponseEntity<UserDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpRequest))
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(userLoginRequest))
    }

    @Operation(summary = "사용자 프로필 수정")
    @PostMapping("/user/info")
    fun userInfo(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody userProfileRequest: UserProfileRequest
    ): ResponseEntity<UserProfileResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.userInfo(user, userProfileRequest))
    }
}