package com.example.backoffice.domain.store.controller

import com.example.backoffice.domain.store.dto.CreateStoreArguments
import com.example.backoffice.domain.store.dto.StoreInfo
import com.example.backoffice.domain.store.dto.StoreResponse
import com.example.backoffice.domain.store.dto.UpdateStoreArguments
import com.example.backoffice.domain.store.service.StoreService
import com.example.backoffice.domain.user.service.UserService
import com.example.backoffice.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/stores")
@RestController
class StoreController(
    val storeService: StoreService,
) {

    @Operation(summary = "가게 등록")
    @PostMapping
    fun createStore(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody createStoreArguments: CreateStoreArguments,
    ): ResponseEntity<StoreResponse> {
        val store = storeService.createStore(createStoreArguments, user)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(store)
    }

    @Operation(summary = "가게목록 조회")
    @GetMapping
    fun getPaginatedStores(
        @PageableDefault(size = 5, sort = ["id"]) pageable: Pageable
    ): ResponseEntity<Page<StoreResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getPaginatedStores(pageable))
    }

    @Operation(summary = "가게 조회")
    @GetMapping("/{storeId}")
    fun getStoreInfo(
        @PathVariable storeId: Long): ResponseEntity<StoreInfo> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getStoreById(storeId))
    }

    @Operation(summary = "가게 정보 수정")
    @PutMapping("/{storeId}")
    fun updateStore(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody updateStoreArguments: UpdateStoreArguments
    ): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.updateStore(storeId, updateStoreArguments, user))
    }

    @Operation(summary = "가게 삭제")
    @DeleteMapping("/{storeId}")
    fun deleteStore(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<String> {
        val storeName = storeService.deleteStore(storeId, user)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("$storeName 가게가 삭제되었습니다.")
    }

}