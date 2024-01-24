package com.example.backoffice.domain.exception

class UserNotFoundException(val email: String):
    RuntimeException("User not found with given email: $email")