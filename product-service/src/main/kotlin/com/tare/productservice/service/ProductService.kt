package com.tare.productservice.service

import com.tare.productservice.dto.ProductRequest
import com.tare.productservice.dto.ProductResponse
import com.tare.productservice.model.Product
import com.tare.productservice.repository.ProductRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun createProduct(productRequest: ProductRequest) {
        val product =
            Product(name = productRequest.name, description = productRequest.description, price = productRequest.price)

        productRepository.save(product)
        log.info("Product ${product.id} is saved")
    }

    fun getAllProducts(): List<ProductResponse> {
        val products = productRepository.findAll()
        return products.map {
            ProductResponse(id = it.id, name = it.name, description = it.description, price = it.price)
        }
    }

}