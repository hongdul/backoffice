package com.example.backoffice.domain.review.model

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    val user: User
) {
    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "created_at")
    private var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "modified_at")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private var modifiedAt: LocalDateTime = LocalDateTime.now()
}