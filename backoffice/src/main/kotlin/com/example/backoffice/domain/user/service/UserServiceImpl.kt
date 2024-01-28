package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.exception.*
import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.domain.user.model.QProfile.profile
import com.example.backoffice.domain.user.model.QUser.user
import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.repository.ProfileRepository
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.UserPrincipal
import com.example.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,

) : UserService {
    @Transactional
    override fun signUp(userSignUpRequest: UserSignUpRequest): UserDto {
        if (userRepository.existsByEmail(userSignUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }
        return UserDto.from(
            userRepository.save(
                User(
                    email = userSignUpRequest.email,
                    password = passwordEncoder.encode(userSignUpRequest.password),
                    role = userSignUpRequest.role
                )
            )
        )
    }

    @Transactional
    override fun login(userLoginRequest: UserLoginRequest): UserLoginResponse {
        val user = userRepository.findByEmail(userLoginRequest.email) ?: throw ModelNotFoundException("user", null)
        if (user.role != userLoginRequest.role || !passwordEncoder.matches(
                userLoginRequest.password,
                user.password
            )
        ) {
            throw InvalidCredentialException()
        }
        return UserLoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun createInfo(userInfoRequest: UserInfoRequest, user: UserPrincipal): ProfileDto {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val existProfile = profileRepository.findByUser(users)
        return existProfile?.let {
            throw ModelAlreadyExistsException("email")
        } ?: ProfileDto.from(profileRepository.save(userInfoRequest.to(users)))
    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun updateInfo(profileId: Long, userInfoRequest: UserInfoRequest, user: UserPrincipal): ProfileDto {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val profiles =
            profileRepository.findByIdAndUser(profileId, users) ?: throw ModelNotFoundException("profile", profileId)
        profiles.changeInfo(userInfoRequest)
        return ProfileDto.from(profileRepository.save(profiles))

    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun getInfo(userId: Long, user: UserPrincipal): ProfileDto {
        val profileId = if (userId == 0L) user.id else userId
        val profiles =
            profileRepository.findByUserId(profileId) ?: throw ModelNotFoundException("profile", profileId)
        return ProfileDto.from(profiles)
    }
}