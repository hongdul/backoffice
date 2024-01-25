package com.example.backoffice.menu.controller

import com.example.backoffice.menu.dto.CreateMenuRequest
import com.example.backoffice.menu.dto.MenuResponse
import com.example.backoffice.menu.dto.UpdateMenuRequest
import com.example.backoffice.menu.service.MenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/stores/{storeId}/menus")
class MenuController(
    private val menuService: MenuService
) {
    fun getAllMenuList(): ResponseEntity<List<MenuResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getAllMenuList())
    }

    @GetMapping("/{menuId}")
    fun getMenuById(@PathVariable storeId: Long, @PathVariable menuId: Long):
            ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getMenuById(storeId, menuId))
    }

    @PostMapping
    fun createMenu(
        @PathVariable storeId: Long,
        @RequestBody createMenuRequest: CreateMenuRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(menuService.createMenu(storeId, createMenuRequest))
    }

    @PutMapping("/{menuId}")
    fun updateMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @RequestBody updateMenuRequest: UpdateMenuRequest
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.updateMenu(storeId, menuId, updateMenuRequest))
    }

    @DeleteMapping("/{menuId}")
    fun deleteMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long
    ): ResponseEntity<Unit> {
        menuService.deleteMenu(storeId, menuId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}