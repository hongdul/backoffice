package com.example.backoffice.domain.menu.service

import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.menu.dto.MenuWithReviews
import com.example.backoffice.domain.menu.dto.MenuArguments
import com.example.backoffice.infra.security.UserPrincipal

interface MenuService {

    fun getMenus(storeId: Long): List<MenuResponse>

    fun getMenuInfo(menuId: Long): MenuWithReviews

    fun registerMenu(arguments: MenuArguments, storeId: Long, user: UserPrincipal): MenuResponse

    fun updateMenu(arguments: MenuArguments, menuId: Long, user: UserPrincipal): MenuResponse

    fun deleteMenu(menuId: Long, user: UserPrincipal): String

    fun changeMenuStatus(menuId: Long, user: UserPrincipal): String
}