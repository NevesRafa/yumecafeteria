package com.example.yumecafeteria.data.model

import com.example.yumecafeteria.data.local.entity.ProductEntity
import com.example.yumecafeteria.data.local.entity.ProductOrderEntity

data class ProductCart(
    val product: ProductEntity,
    var quantity: Int
)

object ProductOrderMapper {

    fun map(productCart: ProductCart, orderId: Long): ProductOrderEntity {

        return ProductOrderEntity(
            id = 0,
            productId = productCart.product.id,
            orderId = orderId,
            quantity = productCart.quantity
        )
    }
}