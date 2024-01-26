package com.example.backoffice.domain.menu.dto

import com.example.backoffice.domain.menu.model.Menu
import com.example.backoffice.domain.menu.model.MenuStatus
import com.example.backoffice.domain.store.model.Store

data class MenuArguments(
    val name: String,
    val description: String,
    val price: Long
) {
    fun to(store: Store): Menu {
        return Menu(
            name = this.name,
            description = this.description,
            price = this.price,
            store = store,
            status = MenuStatus.FORSALE
        )
    }
}
