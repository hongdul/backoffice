package com.example.backoffice.menu.dto

import com.example.backoffice.menu.model.MenuStatus

data class MenuResponse(
    val name : String,
    val description : String,
    val status : MenuStatus,
    val price : Long,
    val store : Long?
)
