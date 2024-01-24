package com.example.backoffice.domain.user.controller

import com.example.backoffice.domain.user.dto.UserDto
import com.example.backoffice.domain.user.dto.UserLoginRequest
import com.example.backoffice.domain.user.dto.UserLoginResponse
import com.example.backoffice.domain.user.dto.UserSignUpRequest
import com.example.backoffice.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
}