package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.service.WishlistService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/wishlist")
class WishlistController(
    private val wishlistService: WishlistService
) {
    private fun getUserId(): Long {
        return (SecurityContextHolder.getContext().authentication!!.principal as String).toLong()
    }

    @PostMapping("/{productId}")
    fun add(@PathVariable productId: Long) {
        wishlistService.add(getUserId(), productId)
    }

    @DeleteMapping("/{productId}")
    fun remove(@PathVariable productId: Long) {
        wishlistService.remove(getUserId(), productId)
    }

    @GetMapping
    fun get(): List<Product> {
        return wishlistService.get(getUserId())
    }
}
