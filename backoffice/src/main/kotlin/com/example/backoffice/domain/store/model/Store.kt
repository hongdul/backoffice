package com.example.backoffice.domain.store.model

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.user.model.User
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

    @Column(name = "description", nullable = false)
    var description: String,

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val menus: List<Menu> = emptyList(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    val user: User,
) {

}
