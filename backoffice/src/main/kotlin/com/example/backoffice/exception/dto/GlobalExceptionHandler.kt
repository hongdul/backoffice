package com.example.backoffice.exception.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class GlobalExceptionHandler {

    @ExceptionHandler(StoreNotFoundException :: class)
    fun handlerStoreNotFoundException(e: StoreNotFoundException) : ResponseEntity<ErrorResponse>
    {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }
    fun handlerMenuNotFoundExcetpion(e: MenuNotFoundException) : ResponseEntity<ErrorResponse>
    {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(e.message))
    }
}