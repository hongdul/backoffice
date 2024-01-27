package com.example.backoffice.domain.order.service

import com.example.backoffice.domain.exception.MenuNotFoundException
import com.example.backoffice.domain.exception.UserNotFoundException
import com.example.backoffice.domain.menu.repository.MenuRepository
import com.example.backoffice.domain.order.dto.CartDto
import com.example.backoffice.domain.order.dto.CartRequest
import com.example.backoffice.domain.order.dto.OrderMapDto
import com.example.backoffice.domain.order.model.Cart
import com.example.backoffice.domain.order.model.History
import com.example.backoffice.domain.order.model.OrderMap
import com.example.backoffice.domain.order.model.toDto
import com.example.backoffice.domain.order.repository.CartRepository
import com.example.backoffice.domain.order.repository.HistoryRepository
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
    private val historyRepository: HistoryRepository,
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
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    override fun deleteCart(user: UserPrincipal): String {
        cartRepository.deleteAllByUserId(user.id)
        return "장바구니를 비웠습니다."
    }


    @Transactional
    override fun order(user: UserPrincipal): String {
        val users = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user.id)

        // cart repository 에서 메뉴 id, 메뉴 갯수 Pair 로 묶어 가져오기
        val (menuIdList, countList) = cartRepository.findAllByUserId(user.id)
            ?.let { carts ->
                Pair(carts.map { it.menu.id!! }, carts.map { it.count })
            } ?: throw UserNotFoundException("user", user.id) // 장바구니가 비어있습니다 출력해야함

        val orderHistory = historyRepository.save(History(users)).id
            .let{ historyRepository.findByIdOrNull(it) ?: throw UserNotFoundException("user", user.id) }

        // orderMap 에 저장
        for (l in menuIdList.indices) {
            val menu = menuRepository.findByIdOrNull(menuIdList[l]) ?: throw MenuNotFoundException(menuIdList[l])
            orderMapRepository.save(OrderMap(menu, orderHistory, countList[l]))
        }
        // 삭제쿼리
        cartRepository.deleteAllByUserId(user.id)

        return "주문이 완료되었습니다."
    }

    @Transactional
    override fun getHistory(user: UserPrincipal): MutableList<List<OrderMapDto>> {
        val historyIdList = historyRepository.findAllByUserId(user.id)
            ?: throw Exception("orderHistory Not found")
        val orderHistoryList: MutableList<List<OrderMapDto>> = mutableListOf()
        for (l in historyIdList.indices) {
            val orderHistory = historyIdList[l].id?.
            let { orderMapRepository.findAllByHistoryId(it) }!!.
            map{ it.toDto() }
            orderHistoryList += orderHistory
        }
        return orderHistoryList
    }

}