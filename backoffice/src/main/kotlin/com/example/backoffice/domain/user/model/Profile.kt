package com.example.backoffice.domain.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType

@Entity
@Table(name = "profile")
class Profile(
    @Column(name = "nickname")
    val nickName: String,

    @Column(name = "address")
    val address: String,

    @Column(name = "phone")
    val phoneNumber: VarcharJdbcType,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}