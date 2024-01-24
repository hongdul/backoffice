package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.exception.UserNotFoundException
import com.example.backoffice.domain.user.dto.LoginRequest
import com.example.backoffice.domain.user.dto.UserDto
import com.example.backoffice.domain.user.dto.UserSignUpRequest
import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.model.UserRole
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
) : UserService {
    override fun signUp(userSignUpRequest: UserSignUpRequest): UserDto {
        if (userRepository.existsByEmail(userSignUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }
        val user = User(
            email = userSignUpRequest.email,
            nickname = userSignUpRequest.nickname,
            password = passwordEncoder.encode(userSignUpRequest.password),
            role = UserRole.CUSTOMER
        )

        val createdUser = userRepository.save(user)

        val token = jwtPlugin.generateAccessToken(
            subject = user.id.toString(),
            email = user.email,
            nickname = user.nickname,
            role = user.role.name
        )

        return UserDto.from(createdUser, token)
    }

    override fun login(loginRequest: LoginRequest): UserDto {
        val user = userRepository.findByEmail(loginRequest.email)
            ?: throw UserNotFoundException(loginRequest.email)

        if (user.email != loginRequest.email ||
            !passwordEncoder.matches(loginRequest.password, user.password)
        ) {
            throw Exception("authentication failed")
        }

        val token = jwtPlugin.generateAccessToken(
            subject = user.id.toString(),
            nickname = user.nickname,
            email = user.email,
            role = user.role.name
        )

        return UserDto.from(user, token)
    }
}