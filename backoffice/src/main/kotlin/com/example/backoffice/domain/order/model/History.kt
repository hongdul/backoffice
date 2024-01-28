package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "history")
class History(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(updatable = false)
    val orderedAt: ZonedDateTime = ZonedDateTime.now()

}