package com.example.backoffice.domain.review.model

import com.example.backoffice.domain.menu.model.Menu
import jakarta.persistence.*


@Entity
@Table(name = "reviews")
class Review(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "rating", nullable = false)
    var rating: Int,

    @Column(name = "content", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    val menu: Menu,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", nullable = false)
//    val user: User,

) {
}