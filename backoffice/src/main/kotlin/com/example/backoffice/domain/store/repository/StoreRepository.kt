package com.example.backoffice.domain.store.repository

import com.example.backoffice.domain.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long>, CustomStoreRepository {
}