package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.exception.InvalidCredentialException
import com.example.backoffice.domain.exception.ProfileNotFoundException
import com.example.backoffice.domain.exception.WriterNotMatchedException
import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.domain.user.model.User
import com.example.backoffice.domain.user.repository.ProfileRepository
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
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
    override fun createInfo(userInfoRequest: UserInfoRequest): ProfileDto {
        val saveInfo = profileRepository.save(userInfoRequest.to())
        return ProfileDto.from(saveInfo)

    }

    @Transactional
    override fun updateInfo(profileId: Long, userInfoRequest: UserInfoRequest): ProfileDto {
        val profiles =
            profileRepository.findByIdOrNull(profileId) ?: throw ProfileNotFoundException("profile", profileId)
        profiles.changeInfo(userInfoRequest)
        return ProfileDto.from(profileRepository.save(profiles))

    }

    @Transactional
    override fun getInfo(profileId: Long): ProfileDto {
        val profiles =
            profileRepository.findByIdOrNull(profileId) ?: throw ProfileNotFoundException("profile", profileId)
        return ProfileDto.from(profiles)
    }

}