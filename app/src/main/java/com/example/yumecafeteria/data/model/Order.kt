package com.example.yumecafeteria.data.model

data class Order(
    val id: Long = 0L,
    val products: List<ProductOrder>,
    val totalProcutsCount: Int,
    val totalPrice: Double
)