package com.example.AgroNearBackend.repository

import com.example.AgroNearBackend.Entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    fun findByProductNameContainingIgnoreCase(query: String): List<Product>
}
