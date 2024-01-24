package com.example.backoffice.store.repository

import com.example.backoffice.store.model.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long>
{

}