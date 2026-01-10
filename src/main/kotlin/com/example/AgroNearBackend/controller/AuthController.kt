package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.dto.AuthRequest
import com.example.AgroNearBackend.dto.RegisterRequest
import com.example.AgroNearBackend.dto.TokenPair
import com.example.AgroNearBackend.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest) {
        authService.register(
            request.name,
            request.email,
            request.password
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): TokenPair {
        return authService.login(request.email, request.password)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: TokenPair): TokenPair {
        return authService.refresh(request.refreshToken)
    }
}