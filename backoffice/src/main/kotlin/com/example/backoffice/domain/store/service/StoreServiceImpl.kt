package com.example.backoffice.domain.store.service

import com.example.backoffice.domain.exception.ModelNotFoundException
import com.example.backoffice.domain.store.dto.CreateStoreArguments
import com.example.backoffice.domain.store.dto.StoreInfo
import com.example.backoffice.domain.store.dto.StoreResponse
import com.example.backoffice.domain.store.dto.StoreResponse.Companion.from
import com.example.backoffice.domain.store.dto.UpdateStoreArguments
import com.example.backoffice.domain.store.repository.StoreRepository
import com.example.backoffice.domain.user.repository.UserRepository
import com.example.backoffice.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoreServiceImpl(
    val storeRepository: StoreRepository,
    val userRepository: UserRepository,
    private val pageable: PageableHandlerMethodArgumentResolverCustomizer
) : StoreService {

    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    override fun getPaginatedStores(pageable: Pageable): Page<StoreResponse> {
        return storeRepository.findAll(pageable).map { from(it) }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    override fun getStoreById(storeId: Long): StoreInfo {
        val foundStore = storeRepository.findByIdOrNull(storeId)
            ?: throw ModelNotFoundException("store", storeId)

        return foundStore.let { StoreInfo.from(it) }
    }

    @Transactional
    @PreAuthorize("hasRole('MANAGER')")
    override fun createStore(createStoreArguments: CreateStoreArguments, user: UserPrincipal): StoreResponse {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val createdStore = storeRepository.save(createStoreArguments.to(users))

        return from(createdStore)
    }

    @Transactional
    @PreAuthorize("hasRole('MANAGER')")
    override fun updateStore(
        storeId: Long,
        updateStoreArguments: UpdateStoreArguments,
        user: UserPrincipal
    ): StoreResponse {
        val stores = storeRepository.findByIdAndUser(
            storeId,
            userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        ) ?: throw ModelNotFoundException("store", storeId)
        stores.changeStore(updateStoreArguments)
        val updatedStore = storeRepository.save(stores)
        return from(updatedStore)
    }

    @Transactional
    @PreAuthorize("hasRole('MANAGER')")
    override fun deleteStore(storeId: Long, user: UserPrincipal): String {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val stores = storeRepository.findByIdAndUser(storeId, users) ?: throw ModelNotFoundException("store", storeId)
        storeRepository.delete(stores)
        return "가게가 삭제처리 되었습니다."
    }
}