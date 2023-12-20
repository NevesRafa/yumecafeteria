package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.ProductCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(private val database: ProductDao) {

    private val productCartList = mutableListOf<ProductCart>()

    suspend fun addProductToCartById(id: Int) {
        withContext(Dispatchers.IO) {
            val product = database.searchId(id)
            productCartList.add(ProductCart(product, 1))
        }
    }

    fun getProductsInCart(): List<ProductCart> {
        return productCartList
    }

    fun removeProductFromCart(product: ProductCart) {
        if (productCartList.contains(product)) {
            productCartList.remove(product)
        }
    }

    fun sumQuantity(): Int {

        var quantity = 0

        if (productCartList.isNotEmpty()) {
            quantity = productCartList.sumBy { it.quantity }
        }

        return quantity
    }

    fun sumPrice(): Double {

        var total = 0.0

        if (productCartList.isNotEmpty()) {
            total = productCartList.sumByDouble { it.product.price * it.quantity }
        }

        return total
    }
}