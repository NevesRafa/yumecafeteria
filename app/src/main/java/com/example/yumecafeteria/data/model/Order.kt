package com.example.yumecafeteria.data.model

data class Order(
    val id: Long = 0L,
    val products: List<ProductOrder>,
    val totalProductsCount: Int,
    val totalPrice: Double
)