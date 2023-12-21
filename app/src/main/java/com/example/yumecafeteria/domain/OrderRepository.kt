package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.OrdersDao
import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.local.dao.ProductOrderDao
import com.example.yumecafeteria.data.model.Orders
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.data.model.ProductOrder
import com.example.yumecafeteria.data.model.ProductOrderMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderRepository(
    private val productDao: ProductDao,
    private val ordersDao: OrdersDao,
    private val productOrderDao: ProductOrderDao
) {

    private val productCartList = mutableListOf<ProductCart>()

    suspend fun addProductToCartById(id: Long) {
        withContext(Dispatchers.IO) {
            val product = productDao.searchId(id)
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

    fun sumTotalQuantity(): Int {

        var quantity = 0

        if (productCartList.isNotEmpty()) {
            quantity = productCartList.sumBy { it.quantity }
        }

        return quantity
    }

    fun sumTotalPrice(): Double {

        var total = 0.0

        if (productCartList.isNotEmpty()) {
            total = productCartList.sumByDouble { it.product.price * it.quantity }
        }

        return total
    }

    fun increaseQuantity(productId: Long) {

        val existingProduct = productCartList.find { it.product.id == productId }

        if (existingProduct != null) {
            existingProduct.quantity++
        }
    }

    fun decreaseQuantity(productId: Long) {

        val existingProduct = productCartList.find { it.product.id == productId }

        if (existingProduct != null && existingProduct.quantity != 1) {
            existingProduct.quantity--
        }
    }

    suspend fun insertProductsMock() {
        withContext(Dispatchers.IO) {
            productDao.save(productList)
        }
    }

    suspend fun getAllProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            productDao.getAll()
        }
    }

    suspend fun createOrder() {
        withContext(Dispatchers.IO) {
            val orderId = ordersDao.save(Orders())
            productCartList.forEach {

                productOrderDao.save(ProductOrderMapper.map(it, orderId))
            }

        }
    }

    suspend fun getAllOrders(): List<ProductOrder> {
        return withContext(Dispatchers.IO) {
            productOrderDao.getAll()
        }
    }
}
