package com.tare.productservice

import com.tare.productservice.dto.ProductRequest
import com.tare.productservice.repository.ProductRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.util.Date

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var productRepository: ProductRepository

    private val objectMapper = ObjectMapper()

    companion object {
        @Container
        val mongoDbContainer = MongoDBContainer("mongo:6.0.6")

        @DynamicPropertySource
        fun setProperties(dynamicPropertyRegistry: DynamicPropertyRegistry) {
            dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl)
        }
    }

    @Test
    fun `Create a Product`() {
        val productRequest = getProductRequest()
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest))
        ).andExpect(
            status().isCreated
        )
        assert(productRepository.findAll().size != 1)
        println(productRepository.findAll().size)
    }

    private fun getProductRequest(): ProductRequest {
        return ProductRequest(
            name = "iPhone 13",
            description = "A very smart smart phone ${Date().time}",
            price = "1200"
        )
    }

    @Test
    fun contextLoads() {
    }

}
