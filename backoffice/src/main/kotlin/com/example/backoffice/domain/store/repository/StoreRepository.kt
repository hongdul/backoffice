package com.example.backoffice.domain.store.repository

import com.example.backoffice.domain.store.model.Store
import com.example.backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long>, CustomStoreRepository {
    fun findByIdAndUser(storeId: Long, userId: User): Store?

}