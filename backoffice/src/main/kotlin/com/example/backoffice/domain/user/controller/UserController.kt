package com.example.backoffice.domain.user.controller

import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.domain.user.model.Profile
import com.example.backoffice.domain.user.model.QUser.user
import com.example.backoffice.domain.user.repository.ProfileRepository
import com.example.backoffice.domain.user.service.UserService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    val userService: UserService
) {

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody userSignUpRequest: UserSignUpRequest
    ): ResponseEntity<UserDto> {
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

    @Operation(summary = "사용자 정보 생성")
    @PostMapping
    fun createInfo(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody userInfoRequest: UserInfoRequest
    ): ResponseEntity<ProfileDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.createInfo(userInfoRequest, user))
    }

    @Operation(summary = "사용자 정보 수정")
    @PatchMapping("/{profileId}")
    fun updateInfo(
        @PathVariable profileId: Long,
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody userInfoRequest: UserInfoRequest
    ): ResponseEntity<ProfileDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateInfo(profileId, userInfoRequest, user))
    }

    @Operation(summary = "사용자 정보 조회")
    @GetMapping("/{userId}")
    fun getInfo(
        @PathVariable userId: Long
    ): ResponseEntity<ProfileDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getInfo(userId))
    }

}