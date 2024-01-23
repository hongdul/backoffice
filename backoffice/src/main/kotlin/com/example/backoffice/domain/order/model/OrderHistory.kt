package com.example.backoffice.domain.order.model

import com.example.backoffice.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
class OrderHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val user: User

    ) {

    @CreationTimestamp
    @Column(updatable = false)
    val orderedAt: ZonedDateTime = ZonedDateTime.now()
}