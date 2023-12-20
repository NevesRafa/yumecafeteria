package com.example.yumecafeteria.data.model

data class ProductCart(
    val product: Product,
    var quantity: Int
)


object ProductOrderMapper {

    fun map(productCart: ProductCart, orderId: Long): ProductOrder {

        return ProductOrder(
            id = 0,
            productId = productCart.product.id,
            orderId = orderId,
            quantity = productCart.quantity
        )
    }
}