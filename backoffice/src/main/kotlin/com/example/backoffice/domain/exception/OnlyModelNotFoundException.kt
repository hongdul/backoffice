package com.example.backoffice.domain.exception

data class OnlyModelNotFoundException(private val model: String) :
    RuntimeException("$model not found")