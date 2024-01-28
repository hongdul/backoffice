package com.example.backoffice.domain.exception

data class ModelNotFoundException(private val model: String, private val id: Long?) :
    RuntimeException("$model not found with given $model-Id: $id")