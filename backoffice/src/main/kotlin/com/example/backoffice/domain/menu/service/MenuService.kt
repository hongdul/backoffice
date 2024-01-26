package com.example.backoffice.domain.menu.service

import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.menu.dto.RegisterMenuArguments
import com.example.backoffice.domain.menu.dto.UpdateMenuArguments
import com.example.backoffice.infra.security.UserPrincipal

interface MenuService {

    fun getMenus(storeId: Long): List<MenuResponse>

    fun getMenuInfo(menuId: Long): MenuResponse

    fun registerMenu(arguments: RegisterMenuArguments, storeId: Long, user: UserPrincipal): MenuResponse

    fun updateMenu(arguments: UpdateMenuArguments, menuId: Long, user: UserPrincipal): MenuResponse

    fun deleteMenu(menuId: Long, user: UserPrincipal): String

    fun changeMenuStatus(menuId: Long, user: UserPrincipal): String
}