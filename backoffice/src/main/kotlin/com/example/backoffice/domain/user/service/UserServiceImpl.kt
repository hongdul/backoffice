package com.example.backoffice.domain.user.service

import com.example.backoffice.domain.exception.InvalidCredentialException
import com.example.backoffice.domain.exception.UserNotFoundException
import com.example.backoffice.domain.exception.WriterNotMatchedException
import com.example.backoffice.domain.user.dto.*
import com.example.backoffice.domain.user.model.Profile
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
        // 만약 role 값을 Consumer, Seller 제외 다른값이 들어가면 어떻게되는지, 예외처리 해주기
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

    //사용자 정보 수정및 생성
    @Transactional
    override fun userInfo(user: UserPrincipal, userProfileRequest: UserProfileRequest): UserProfileResponse {
        val profiles = profileRepository.findByIdOrNull(user.id)
        val toProfiles = Profile(
            nickName = userProfileRequest.nickName,
            address = userProfileRequest.address,
            phoneNumber = userProfileRequest.phoneNumber,
            user = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)
        )
        if (profiles == null) {
            profileRepository.save(toProfiles)
        } else {
            profiles.updateProfile(toProfiles)
            profileRepository.save(profiles)
        }
        return UserProfileResponse.from() // todo

    }

}