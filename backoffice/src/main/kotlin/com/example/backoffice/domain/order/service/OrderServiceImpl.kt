package com.example.backoffice.domain.order.service

import com.example.backoffice.domain.exception.MenuNotFoundException
import com.example.backoffice.domain.exception.UserNotFoundException
import com.example.backoffice.domain.menu.dto.MenuArguments
import com.example.backoffice.domain.menu.repository.MenuRepository
import com.example.backoffice.domain.order.dto.CartDto
import com.example.backoffice.domain.order.dto.CartRequest
import com.example.backoffice.domain.order.model.Cart
import com.example.backoffice.domain.order.model.OrderHistory
import com.example.backoffice.domain.order.model.OrderMap
import com.example.backoffice.domain.order.repository.CartRepository
import com.example.backoffice.domain.order.repository.OrderHistoryRepository
import com.example.backoffice.domain.order.repository.OrderMapRepository
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    private val cartRepository: CartRepository,
    private val menuRepository: MenuRepository,
    private val userRepository: UserRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
    private val orderMapRepository: OrderMapRepository
) : OrderService {
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    @Transactional
    override fun menuInCart(menuId: Long, user: UserPrincipal, cartRequest: CartRequest): CartDto {
        val menus = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException(menuId)
        val users = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)
        val carts = cartRepository.save(
            Cart(
                menu = menus,
                user = users,
                count = cartRequest.count
            )
        )
        return CartDto.from(carts)
    }

    @Transactional
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun getCart(user: UserPrincipal): List<CartDto> {
        val cart = cartRepository.findAllByUserId(user.id) ?: throw UserNotFoundException("user", user.id)
        return cart.map { CartDto.from(it) }
    }

    @Transactional
    override fun order(user: UserPrincipal) {
        val users = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)
        val orderHistoryId = orderHistoryRepository.save(OrderHistory(users)).id!!
        // 주문내역 저장
        val orderHistory = orderHistoryRepository.findByIdOrNull(orderHistoryId) ?: throw UserNotFoundException(
            "user",
            user.id
        )
        val (menuIdList, countList) = cartRepository.findAllByUserId(user.id)
            ?.let { carts ->
                Pair(carts.map { it.menu.id!! }, carts.map { it.count })
            } ?: throw UserNotFoundException("user", user.id)

        for (l in menuIdList.indices) {
            val menu = menuRepository.findByIdOrNull(menuIdList[l]) ?: throw MenuNotFoundException(menuIdList[l])
            orderMapRepository.save(OrderMap(menu, orderHistory, countList[l]))
        }
        //orderMap 으로 보내기
        cartRepository.deleteAllByUserId(user.id)
        // 삭제쿼리
    }

    @Transactional
    override fun getOrderHistory(user: UserPrincipal): List<List<Pair<MenuArguments, Int>>> {
        val historyIdList = orderHistoryRepository.findAllByUserId(user.id) ?: throw Exception("orderHistory Not found")
        for (l in historyIdList.indices) {
            val (menuIdList, countList) = orderMapRepository.findAllByOrderHistoryId(historyIdList[l])
                ?.let { orderMap ->
                    Pair(orderMap.map { it.menu.id!! }, orderMap.map { it.count })
                } ?: throw UserNotFoundException("user", user.id)
        }


    }

}