package com.tare.orderservice.model

import jakarta.persistence.*

@Entity
@Table(name = "t_orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long? = null,
    var orderNumber: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    var orderLineItems: List<OrderLineItem>? = null
)