package com.example.backoffice.domain.store.service

import com.example.backoffice.domain.exception.ModelNotFoundException
import com.example.backoffice.domain.store.repository.StoreRepository
import com.example.backoffice.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer


@SpringBootTest
@ExtendWith(MockKExtension::class)
class StoreServiceTest : BehaviorSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val storeRepository = mockk<StoreRepository>()
    val userRepository = mockk<UserRepository>()
    val pageable = mockk<PageableHandlerMethodArgumentResolverCustomizer>()

    val storeService = StoreServiceImpl(storeRepository, userRepository, pageable)

    Given("Store 목록이 존재하지 않을 때") {
        When("특정 Store를 요청하면") {
            Then("ModelNotFoundException이 발생해야한다.") {

                // given
                val storeId = 1L
                every { storeRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ModelNotFoundException> {
                    storeService.getStoreById(storeId)
                }
            }
        }

//        When("Course목록을 요청하면") ~

    }
})