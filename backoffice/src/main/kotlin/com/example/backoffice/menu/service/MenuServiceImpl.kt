package com.example.backoffice.menu.service

import com.example.backoffice.exception.dto.MenuNotFoundException
import com.example.backoffice.menu.dto.CreateMenuRequest
import com.example.backoffice.menu.dto.MenuResponse
import com.example.backoffice.menu.dto.UpdateMenuRequest
import com.example.backoffice.menu.model.Menu
import com.example.backoffice.menu.model.toResponse
import com.example.backoffice.menu.repository.MenuRepository
import org.springframework.data.repository.findByIdOrNull

class MenuServiceImpl(
    private val menuRepository: MenuRepository
) : MenuService
{
    override fun getAllMenuList(): List<MenuResponse> {
        return menuRepository.findAll().map { it.toResponse() }
    }

    override fun getMenuById(storeId: Long, menuId: Long): MenuResponse {
        val menu = menuRepository.findByIdOrNull(menuId)
            ?: throw MenuNotFoundException("Menu", menuId)
        return menu.toResponse()
    }

    override fun createMenu(storeId: Long, request: CreateMenuRequest): MenuResponse {
       return menuRepository.save(
           Menu(
               name = request.name,
               description = request.description,
               status = request.status,
               price = request.price,
               store = request.store
           )
       ).toResponse()
    }

    override fun updateMenu(storeId: Long, menuId: Long, request: UpdateMenuRequest): MenuResponse {
        val menu = menuRepository.findByIdOrNull(menuId)
            ?: throw MenuNotFoundException("Menu", menuId)
        val(name,description,status, price, id) = request
        menu.name = name
        menu.description = description
        menu.status = status
        menu.price = price
        menu.id = id

        return menuRepository.save(menu).toResponse()
    }

    override fun deleteMenu(storeId: Long, menuId: Long) {
        val menu = menuRepository.findByIdOrNull(menuId)
            ?: throw MenuNotFoundException("Menu", menuId)
        menuRepository.delete(menu)
    }
}