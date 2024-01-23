package com.example.backoffice.exception.dto

data class MenuNotFoundException(val menuName : String, val id : Long) : RuntimeException(
    " Menu $menuName not found with given id : $id"
)
