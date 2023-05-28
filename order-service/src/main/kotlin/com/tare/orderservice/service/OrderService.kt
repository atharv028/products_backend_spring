package com.tare.orderservice.service

import com.tare.orderservice.dto.InventoryResponse
import com.tare.orderservice.dto.OrderLineItemDto
import com.tare.orderservice.dto.OrderRequest
import com.tare.orderservice.event.OrderPlacedEvent
import com.tare.orderservice.model.Order
import com.tare.orderservice.model.OrderLineItem
import com.tare.orderservice.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient.Builder
import java.util.UUID

@Service
@Transactional
class OrderService(private val orderRepository: OrderRepository,private val kafkaTemplate: KafkaTemplate<String,OrderPlacedEvent>) {

    @Autowired
    lateinit var webClientBuilder: Builder


    fun placeOrder(orderRequest: OrderRequest): String {
        val order = Order().apply { orderNumber = UUID.randomUUID().toString() }
        val orderLineItems = orderRequest.orderLineItems.map(this::mapToDto)
        order.orderLineItems = orderLineItems

        val skuCodes = orderLineItems.map {
            it.skuCode
        }

        val inventoryResponse = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory") { it.queryParam("skuCode", skuCodes).build() }
            .retrieve()
            .bodyToMono(Array<InventoryResponse>::class.java)
            .block()

        val availableProducts = inventoryResponse?.all { it.isInStock == true }

        return if (availableProducts == true) {
            orderRepository.save(order)
            kafkaTemplate.send("notificationTopic",OrderPlacedEvent(order.orderNumber))
            "Order Placed Successfully!"
        } else {
            throw IllegalArgumentException("Product is not in stock, try again later!!")
        }

    }

    private fun mapToDto(orderLineItemDto: OrderLineItemDto): OrderLineItem {
        return OrderLineItem(
            skuCode = orderLineItemDto.skuCode,
            price = orderLineItemDto.price,
            quantity = orderLineItemDto.quantity
        )
    }
}