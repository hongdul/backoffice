package com.example.backoffice.domain.exception

class MenuNotFoundException (val menuId: Long?):
    RuntimeException("Menu not found with given menuId: $menuId")