package com.tare.orderservice.event

data class OrderPlacedEvent(
    var orderNumber: String? = null
) {
}