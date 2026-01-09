package com.example.AgroNearBackend.dto

import jakarta.annotation.Nonnull
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class RegisterRequest(
    @field:Pattern (regexp = "^[A-Za-z]+(\\s[A-Za-z]+)+$",
    message = "Please enter full name (first name and last name)"
)
    val name: String,
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{9,}$",
        message = "Password must be at least 9 characters long and contain at least one digit, one uppercase and one lowercase letter."
    )
    val password: String
)
