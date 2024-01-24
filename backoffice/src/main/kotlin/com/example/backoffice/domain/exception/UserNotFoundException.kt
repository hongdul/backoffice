package com.example.backoffice.domain.exception

data class UserNotFoundException(val modelName: String, val id: Long?) : RuntimeException(
    "Model $modelName not found with given id: $id"
)