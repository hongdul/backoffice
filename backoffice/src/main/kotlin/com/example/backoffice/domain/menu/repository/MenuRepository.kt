package com.example.backoffice.domain.menu.repository

import com.example.backoffice.domain.menu.model.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
}
