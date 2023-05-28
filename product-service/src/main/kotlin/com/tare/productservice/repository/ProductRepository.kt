package com.tare.productservice.repository

import com.tare.productservice.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository: MongoRepository<Product,String> {
}