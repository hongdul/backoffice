package com.example.backoffice.domain.store.repository

import com.example.backoffice.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class StoreRepositoryImpl: QueryDslSupport(), CustomStoreRepository {

}