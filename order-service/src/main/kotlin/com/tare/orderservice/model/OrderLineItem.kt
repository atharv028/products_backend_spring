package com.tare.orderservice.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "t_order_line_items")
data class OrderLineItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    var skuCode: String? = null,
    var price: BigDecimal? = null,
    var quantity: Int? = null
)