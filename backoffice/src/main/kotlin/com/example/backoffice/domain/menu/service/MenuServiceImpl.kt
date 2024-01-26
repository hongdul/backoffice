package com.example.backoffice.domain.menu.service

import com.example.backoffice.domain.exception.MenuNotFoundException
import com.example.backoffice.domain.exception.StoreNotFoundException
import com.example.backoffice.domain.menu.dto.MenuResponse
import com.example.backoffice.domain.menu.dto.MenuResponse.Companion.from
import com.example.backoffice.domain.menu.dto.MenuWithReviews
import com.example.backoffice.domain.menu.dto.MenuWithReviews.Companion.infoFrom
import com.example.backoffice.domain.menu.dto.MenuArguments
import com.example.backoffice.domain.menu.model.toResponse
import com.example.backoffice.domain.menu.repository.MenuRepository
import com.example.backoffice.domain.store.repository.StoreRepository
import com.example.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MenuServiceImpl(
    val menuRepository: MenuRepository,
    val storeRepository: StoreRepository,
): MenuService {

    override fun getMenus(storeId: Long): List<MenuResponse> {
        val menus = menuRepository.findAll().filter{ it.store.id == storeId }
        return menus.map{ it.toResponse() }
    }

    override fun getMenuInfo(menuId: Long): MenuWithReviews {
        val foundMenu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException(menuId)
        return infoFrom(foundMenu)
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Transactional
    override fun registerMenu(arguments: MenuArguments, storeId: Long, user: UserPrincipal): MenuResponse {
        val foundStore = storeRepository.findByIdOrNull(storeId) ?: throw StoreNotFoundException(storeId)
        if (foundStore.user.id != user.id) throw Exception("no permission")
        return menuRepository.save(arguments.to(foundStore)).toResponse()
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Transactional
    override fun updateMenu(arguments: MenuArguments, menuId: Long, user: UserPrincipal): MenuResponse {
        val foundMenu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException(menuId)
        foundMenu.checkAuthorization(user.id)
        foundMenu.updateBy(arguments)
        return from(foundMenu)
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Transactional
    override fun deleteMenu(menuId: Long, user: UserPrincipal): String {
        val foundMenu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException(menuId)
        foundMenu.checkAuthorization(user.id)
        val name = foundMenu.name
        menuRepository.delete(foundMenu)
        return "$name 메뉴가 삭제되었습니다."
    }

    override fun changeMenuStatus(menuId: Long, user: UserPrincipal): String {
        val foundMenu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException(menuId)
        foundMenu.checkAuthorization(user.id)
        val status = foundMenu.toggleStatus()
        return "${foundMenu.name}의 상태가 $status 로 변경되었습니다."
    }
}