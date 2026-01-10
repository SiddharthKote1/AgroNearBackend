package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.Entity.Wishlist
import com.example.AgroNearBackend.repository.ProductRepository
import com.example.AgroNearBackend.repository.WishlistRepository
import org.springframework.stereotype.Service

@Service
class WishlistService(
    private val wishlistRepo: WishlistRepository,
    private val productRepo: ProductRepository
) {

    fun add(userId: Long, productId: Long) {
        val exists = wishlistRepo.findByUserIdAndProductId(userId, productId)
        if (exists != null) return

        val product = productRepo.findById(productId).orElseThrow()
        wishlistRepo.save(Wishlist(userId = userId, product = product))
    }

    fun remove(userId: Long, productId: Long) {
        val item = wishlistRepo.findByUserIdAndProductId(userId, productId) ?: return
        wishlistRepo.delete(item)
    }

    fun get(userId: Long): List<Product> {
        return wishlistRepo.findByUserId(userId).map { it.product }
    }
}
