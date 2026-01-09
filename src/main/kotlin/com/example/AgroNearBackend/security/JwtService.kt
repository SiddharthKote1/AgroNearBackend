package com.example.AgroNearBackend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date

@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val jwtSecret: String
) {
    private val secretKey= Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))
    private val accessTokenValidityMs= 15L *60*1000L
    val refreshTokenValidityMs=30L * 24*60* 60 *1000L

    private fun generateToken(
        userId: Long,
        type: String,
        expiry: Long
    ): String {
        val now = Date()
        val expiryDate = Date(now.time + expiry)

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("type", type)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    fun generateAccessToken(userId: Long): String{
        return generateToken(userId,"access",accessTokenValidityMs)
    }

    fun generateRefreshToken(userId: Long):String{
        return generateToken(userId,"refresh",refreshTokenValidityMs)
    }

    fun validateAccessToken(token: String): Boolean{
        val claims=parseAllClaims(token) ?: return false
        val tokenType=claims["type"] as? String ?: return false
        return tokenType == "access"
    }

    fun validateRefreshToken(token: String): Boolean{
        val claims=parseAllClaims(token) ?: return false
        val tokenType=claims["type"] as? String ?: return false
        return tokenType == "refresh"
    }

    //Authorisation
    fun getUserIdFromToken(token: String):String{

        val claims=parseAllClaims(token)?: throw IllegalArgumentException("Invalid token")
        return claims.subject
    }


    private fun parseAllClaims(token: String): Claims? {
        val rawToken = if(token.startsWith("Bearer ")){
            token.removePrefix("Bearer ")
        }
        else token
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            null
        }
    }
}