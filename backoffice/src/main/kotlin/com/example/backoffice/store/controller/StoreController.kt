package com.example.backoffice.store.controller

import com.example.backoffice.store.dto.CreateStoreRequest
import com.example.backoffice.store.dto.StoreResponse
import com.example.backoffice.store.dto.UpdateStoreRequest
import com.example.backoffice.store.service.StoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/stores")
@RestController
class StoreController(
    private val storeService: StoreService
) {
    @GetMapping
    fun getStoreList(): ResponseEntity<List<StoreResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getAllStoreList())
    }

    @GetMapping("/{storeId}")
    fun getStore(@PathVariable storeId: Long): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.getStoreById(storeId))
    }

    @PostMapping
    fun createStore(@RequestBody createStoreRequest: CreateStoreRequest): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(storeService.createStore(createStoreRequest))
    }

    @PutMapping("/{storeId}")
    fun updateStore(
        @PathVariable storeId: Long,
        @RequestBody updateStoreRequest: UpdateStoreRequest
    ): ResponseEntity<StoreResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(storeService.updateStore(storeId, updateStoreRequest))
    }

    @DeleteMapping("/{storeId}")
    fun deleteStore(@PathVariable storeId: Long): ResponseEntity<Unit> {
        storeService.deleteStore(storeId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}