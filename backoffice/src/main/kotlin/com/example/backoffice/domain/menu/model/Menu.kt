package com.example.backoffice.domain.menu.model

import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.menu.dto.UpdateMenuArguments
import com.example.backoffice.domain.review.model.Review
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

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val reviews: MutableList<Review> = mutableListOf()
) {
    fun checkAuthorization(userId: Long) {
        if (userId != store.user.id) {
            throw Exception("not permitted")
        }
    }

    fun toggleStatus(): String {
        status = if (this.status == MenuStatus.FORSALE) {
             MenuStatus.SOLDOUT
        } else { MenuStatus.FORSALE }
        return status.name
    }
    fun updateBy(arguments: UpdateMenuArguments) {
        name = arguments.name
        description = arguments.description
        price = arguments.price
    }
}

fun Menu.toResponse(): MenuResponse {
    return MenuResponse(
        id = id!!,
        name = name,
        description = description,
        status = status,
        price = price,
        storeId = store.id!!
    )

}