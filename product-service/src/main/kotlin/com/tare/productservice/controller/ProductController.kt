package com.tare.productservice.controller

import com.tare.productservice.dto.ProductRequest
import com.tare.productservice.dto.ProductResponse
import com.tare.productservice.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(private val productService: ProductService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody req: ProductRequest) {
        productService.createProduct(req)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProducts(): List<ProductResponse> {
        return productService.getAllProducts()
    }
}