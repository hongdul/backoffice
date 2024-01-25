package com.example.backoffice.menu.service

import com.example.backoffice.menu.dto.CreateMenuRequest
import com.example.backoffice.menu.dto.MenuResponse
import com.example.backoffice.menu.dto.UpdateMenuRequest

interface MenuService {
    fun getAllMenuList(): List<MenuResponse>

    fun getMenuById(storeId : Long, menuId : Long) : MenuResponse
    fun createMenu(storeId: Long, request : CreateMenuRequest): MenuResponse

    fun updateMenu(storeId: Long, menuId: Long, request:UpdateMenuRequest) : MenuResponse

    fun deleteMenu(storeId: Long, menuId: Long)
}