package com.example.backoffice.domain.store.controller

import com.example.backoffice.domain.store.dto.StoreInfo
import com.example.backoffice.domain.store.model.StoreStatus
import com.example.backoffice.domain.store.service.StoreService
import com.example.backoffice.infra.security.jwt.JwtPlugin
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest
@AutoConfigureMockMvc // mockMvc 주입용도
@ExtendWith(MockKExtension::class) // mockk쓸 때 표기 해줘야 함
class StoreControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
) : DescribeSpec({
    extension(SpringExtension)

    // describe 끝날 때 마다 설정한 mocking 비워줌
    afterContainer {
        clearAllMocks()
    }

    val storeService = mockk<StoreService>()

    describe("GET /stores/{storeId}") {
        context("존재하는 ID를 요청할 때") {
            it("200 status code를 응답한다") {
                val storeId = 4L

                // Mock 동작 정의
                every {storeService.getStoreById(any()) } returns StoreInfo(
                    id = storeId,
                    name = "test_name",
                    phone = "test_phone",
                    address = "test_address",
                    status = StoreStatus.OPEN,
                    description = "test_description",
                    menus = mutableListOf()
                )
                // AccessToken 생성
                val jwtToken = jwtPlugin.generateAccessToken(
                        subject = "1",
                        email = "test@gmail.com",
                        role = "CUSTOMER"
                )
                // 요청 수행
                val result = mockMvc.perform(
                    get("/stores/$storeId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                // status code 확인
                result.response.status shouldBe 200

                // json string 으로 부터 StoreInfo 생성
                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    StoreInfo::class.java
                )

                responseDto.id shouldBe storeId
            }
        }
    }


})