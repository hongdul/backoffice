package com.example.backoffice.domain.menu.controller

import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.menu.dto.RegisterMenuArguments
import com.example.backoffice.domain.menu.dto.UpdateMenuArguments
import com.example.backoffice.domain.menu.service.MenuService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/menus")
@RestController
class MenuController(
    val menuService: MenuService,
) {
    @Operation(summary = "메뉴 목록 조회")
    @GetMapping
    fun getMenus(@RequestParam(value = "storeId", required = true) storeId: Long)
    : ResponseEntity<List<MenuResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getMenus(storeId))
    }

    @Operation(summary = "메뉴 상세 조회")
    @GetMapping("/{menuId}")
    fun getMenuInfo(@PathVariable menuId: Long): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getMenuInfo(menuId))
    }

    @Operation(summary = "메뉴 등록")
    @PostMapping
    fun registerMenu(arguments: RegisterMenuArguments,
                     @RequestParam(value = "storeId") storeId: Long,
                     @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(menuService.registerMenu(arguments, storeId, user))
    }

    @Operation(summary = "메뉴 수정하기")
    @PutMapping("/{menuId}")
    fun updateMenu(@PathVariable menuId: Long,
                   arguments: UpdateMenuArguments,
                   @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<MenuResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.updateMenu(arguments, menuId, user))
    }

    @Operation(summary = "메뉴 삭제하기")
    @DeleteMapping("/{menuId}")
    fun deleteMenu(@PathVariable menuId: Long,
                   @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.deleteMenu(menuId, user))
    }

    @Operation(summary = "메뉴 상태 변경")
    @PutMapping("/status")
    fun changeMenuStatus(@RequestParam menuId: Long,
                         @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.changeMenuStatus(menuId, user))
    }
}