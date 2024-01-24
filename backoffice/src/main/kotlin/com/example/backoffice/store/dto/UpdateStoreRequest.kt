package com.example.backoffice.store.dto

import com.example.backoffice.store.model.StoreStatus

data class UpdateStoreRequest (
    val name : String,
    val address : String,
    val phone : String,
    val status : StoreStatus,
    val userid : Long?,
    val description : String

)