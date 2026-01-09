package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.service.ProductService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun addProduct(@RequestBody product: Product): Product {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        return productService.addProduct(product)
    }

    @GetMapping
    fun getAllProducts(): List<Product> {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        return productService.getAllProducts()
    }

    @GetMapping("/search")
    fun searchProducts(@RequestParam q: String): List<Product> {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        return productService.searchProducts(q)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): Product {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        return productService.getProductById(id)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody updatedProduct: Product
    ): Product {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        return productService.updateProduct(id, updatedProduct)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        val userId = SecurityContextHolder.getContext().authentication!!.principal as String
        productService.removeProduct(id)
    }
}

