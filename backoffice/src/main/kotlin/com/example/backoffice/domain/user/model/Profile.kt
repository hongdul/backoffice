package com.example.backoffice.domain.user.model

import com.example.backoffice.domain.user.dto.UserInfoRequest
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "profile")
class Profile(
    @Column(name = "nickname")
    var nickName: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "phone")
    var phoneNumber: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    var user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun changeInfo(userInfoRequest: UserInfoRequest) {
        this.nickName = userInfoRequest.nickName
        this.address = userInfoRequest.address
        this.phoneNumber = userInfoRequest.phoneNumber
    }
}