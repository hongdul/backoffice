package com.example.backoffice.domain.store.service

import com.example.backoffice.domain.exception.StoreNotFoundException
import com.example.backoffice.domain.store.dto.CreateStoreArguments
import com.example.backoffice.domain.store.dto.StoreInfo
import com.example.backoffice.domain.store.dto.StoreResponse
import com.example.backoffice.domain.store.dto.StoreResponse.Companion.from
import com.example.backoffice.domain.store.dto.UpdateStoreArguments
import com.example.backoffice.domain.store.repository.StoreRepository
import com.example.backoffice.domain.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
import org.springframework.stereotype.Service

@Service
class StoreServiceImpl(
    val storeRepository: StoreRepository,
//    val userRepository: UserRepository,
    private val pageable: PageableHandlerMethodArgumentResolverCustomizer
) : StoreService {
    override fun getPaginatedStores(pageable: Pageable): Page<StoreResponse> {
        return storeRepository.findAll(pageable).map{ from(it) }
    }

    override fun getStoreById(storeId: Long): StoreInfo {
        val foundStore = storeRepository.findByIdOrNull(storeId)
            ?: throw StoreNotFoundException(storeId)

        return foundStore.let{ StoreInfo.from(it) }
    }

    override fun createStore(createStoreArguments: CreateStoreArguments, seller: User): StoreResponse {
        val createdStore = storeRepository.save(createStoreArguments.to(seller))

        return StoreResponse.from(createdStore)
    }

    override fun updateStore(storeId: Long, updateStoreArguments: UpdateStoreArguments): StoreResponse {
        TODO("Not yet implemented")
    }

    override fun deleteStore(storeId: Long): String {
        TODO("Not yet implemented")
    }
}