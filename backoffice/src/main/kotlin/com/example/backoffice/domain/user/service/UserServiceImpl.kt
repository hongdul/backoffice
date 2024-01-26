package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.exception.InvalidCredentialException
import com.example.backoffice.domain.exception.ProfileNotFoundException
import com.example.backoffice.domain.exception.UserNotFoundException
import com.example.backoffice.domain.exception.WriterNotMatchedException
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
        val user = userRepository.findByEmail(userLoginRequest.email) ?: throw WriterNotMatchedException("user", null)
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
        val users = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)
        val existProfile = profileRepository.findByUser(users)
        return existProfile?.let {
            throw UserNotFoundException("중복됨.", user.id)
        } ?: ProfileDto.from(profileRepository.save(userInfoRequest.to(users)))
    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun updateInfo(profileId: Long, userInfoRequest: UserInfoRequest, user: UserPrincipal): ProfileDto {
        val users = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)
        val profiles =
            profileRepository.findByIdAndUser(profileId, users) ?: throw ProfileNotFoundException("profile", profileId)
        profiles.changeInfo(userInfoRequest)
        return ProfileDto.from(profileRepository.save(profiles))

    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun getInfo(userId: Long): ProfileDto {
        val profiles =
            profileRepository.findByUserId(userId) ?: throw ProfileNotFoundException("profile", userId)
        return ProfileDto.from(profiles)
    }
}