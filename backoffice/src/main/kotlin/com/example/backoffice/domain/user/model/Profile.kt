package com.example.backoffice.domain.user.model

import com.example.backoffice.domain.user.dto.UserInfoRequest
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

@Entity
@Table(name = "profile")
class Profile(
    @Column(name = "nickname")
    var nickName: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "phone")
    var phoneNumber: VarcharJdbcType,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    var user: User? = null // to매서드를 사용하려고 일단 null 로 해놧는데 다른문제 생길지 고민
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