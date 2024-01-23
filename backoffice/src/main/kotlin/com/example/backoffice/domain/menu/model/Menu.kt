package com.example.backoffice.domain.menu.model

import com.example.backoffice.domain.store.model.Store
import jakarta.persistence.*


@Entity
@Table(name = "menus")
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "status", nullable = false)
    var status: MenuStatus,

    @Column(name = "price", nullable = false)
    var price: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false)
    val store: Store,

//    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    val reviews = MutableList<Review> = mutableListOf()

) {

}