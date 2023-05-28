package com.tare.inventoryservice.model

import jakarta.persistence.*

@Entity
@Table(name = "t_inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    var skuCode: String? = null,
    var quantity: Int? = null
)