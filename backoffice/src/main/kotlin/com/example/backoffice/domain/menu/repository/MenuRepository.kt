package com.example.backoffice.domain.menu.repository

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
    fun findStoreIdById(menuId: Long): Long?
}
