package com.tare.orderservice.dto


data class OrderRequest(
    var orderLineItems: List<OrderLineItemDto>,
)