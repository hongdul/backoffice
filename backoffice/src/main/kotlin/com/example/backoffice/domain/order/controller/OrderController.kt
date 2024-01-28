package com.example.backoffice.domain.order.controller

import com.example.backoffice.domain.order.dto.CartDto
import com.example.backoffice.domain.order.dto.CartRequest
import com.example.backoffice.domain.order.dto.OrderMapDto
import com.example.backoffice.domain.order.service.OrderService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @Operation(summary = "장바구니 담기", description = "장바구니에 메뉴담기")
    @PostMapping
    fun menuInCart(
        @RequestParam menuId: Long,
        @RequestBody cartRequest: CartRequest,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<CartDto> {
        val carts = orderService.menuInCart(menuId, user, cartRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(carts)
    }

    @Operation(summary = "장바구니 조회")
    @GetMapping("/cart")
    fun getCart(
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<List<CartDto>> {
        val carts = orderService.getCart(user)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(carts)
    }

    @Operation(summary = "장바구니 비우기")
    @DeleteMapping("/cart/delete")
    fun deleteCart(
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.deleteCart(user))
    }


    @Operation(summary = "주문하기", description = "장바구니에 있는 메뉴를 주문합니다. ")
    @PutMapping("/order")
    fun order(@AuthenticationPrincipal user: UserPrincipal): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.order(user))
    }

    @Operation(summary = "주문내역 조회", description = "주문했던 내역을 목록으로 조회합니다.")
    @GetMapping("/history")
    fun getOrderHistory(
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<List<List<OrderMapDto>>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getHistory(user))
    }
}