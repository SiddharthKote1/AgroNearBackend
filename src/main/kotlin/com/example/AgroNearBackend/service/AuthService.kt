package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.TokenPair
import com.example.AgroNearBackend.repository.UserRepository
import com.example.AgroNearBackend.security.HashEncoder
import com.example.AgroNearBackend.security.JwtService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val hashEncoder: HashEncoder
){
    fun register(name:String,email: String,password:String): User {

        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("Email already registered")
        }

        return userRepository.save(
            User(
                name = name,
                email = email,
                hashedPassword = hashEncoder.encode(password)
            )
        )
    }

    fun login(email:String,password:String): TokenPair {
        val user=userRepository.findByEmail(email)
            ?: throw BadCredentialsException("Invalid credentials.")
        if(!hashEncoder.matches(password,user.hashedPassword)){
            throw BadCredentialsException("Invalid credentials.")
        }

        val newAccessToken= jwtService.generateAccessToken(user.id)
            val newRefreshToken= jwtService.generateRefreshToken(user.id)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    fun refresh(refreshToken: String): TokenPair {

        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Invalid refresh token")
        }

        val userId = jwtService.getUserIdFromToken(refreshToken)

        val newAccessToken = jwtService.generateAccessToken(userId.toLong())
        val newRefreshToken = jwtService.generateRefreshToken(userId.toLong())

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }
}