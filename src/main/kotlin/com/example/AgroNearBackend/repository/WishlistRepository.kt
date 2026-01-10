package com.example.AgroNearBackend.repository

import com.example.AgroNearBackend.Entity.Wishlist
import org.springframework.data.jpa.repository.JpaRepository

interface WishlistRepository : JpaRepository<Wishlist, Long> {

    fun findByUserId(userId: Long): List<Wishlist>

    fun findByUserIdAndProductId(userId: Long, productId: Long): Wishlist?
}
