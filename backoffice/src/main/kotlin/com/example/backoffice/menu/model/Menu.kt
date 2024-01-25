package com.example.backoffice.menu.model

import com.example.backoffice.menu.dto.MenuResponse
import com.example.backoffice.store.model.Store
import jakarta.persistence.*

@Entity
@Table(name = "Menu")
class Menu(
    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "price")
    var price: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: MenuStatus,

    @ManyToOne
    @JoinColumn(name = "store_id")
    var store: Store

    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
}
fun Menu.toResponse(): MenuResponse{
    return MenuResponse(
        name = name,
        description = description,
        status = status,
        price = price,
        store = id,
    )
}