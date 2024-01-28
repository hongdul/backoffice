package com.example.backoffice.domain.exception

data class ModelAlreadyExistsException (private val model: String) :
    RuntimeException("$model is already exists.")