package com.example.yumecafeteria.data.model

import com.example.yumecafeteria.data.local.entity.ProductEntity

data class ProductOrder(
    val product: ProductEntity,
    val quantity: Int,
)
