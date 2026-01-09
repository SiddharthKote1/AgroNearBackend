package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun addProduct(product: Product) =
        productRepository.save(product)

    fun removeProduct(id: Long) =
        productRepository.deleteById(id)

    fun getAllProducts(): List<Product> =
        productRepository.findAll()

    fun getProductById(id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }
    }

    fun updateProduct(id: Long, updatedProduct: Product): Product {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }

        val productToSave = existingProduct.copy(
            productName = updatedProduct.productName,
            productPrice = updatedProduct.productPrice,
            quantity = updatedProduct.quantity,
            sellerName = updatedProduct.sellerName
        )

        return productRepository.save(productToSave)
    }


    fun searchProducts(query: String): List<Product> =
        productRepository.findByProductNameContainingIgnoreCase(query)
}