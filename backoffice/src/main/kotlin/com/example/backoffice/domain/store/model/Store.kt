package com.example.backoffice.domain.store.model

import jakarta.persistence.*

@Entity
@Table(name = "stores")
class Store(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "phone", nullable = false)
    var phone: String,

    @Column(name = "addres", nullable = false)
    var address: String,

    @Column(name = "status", nullable = false)
    var status: StoreStatus,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", nullable = false)
//    val user = User,

) {
}