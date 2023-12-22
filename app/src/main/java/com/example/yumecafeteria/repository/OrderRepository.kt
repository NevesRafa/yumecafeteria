package com.example.yumecafeteria.repository

import com.example.yumecafeteria.data.local.dao.OrdersDao
import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.local.dao.ProductOrderDao
import com.example.yumecafeteria.data.local.entity.OrderEntity
import com.example.yumecafeteria.data.local.entity.ProductEntity
import com.example.yumecafeteria.data.model.Order
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.data.model.ProductOrder
import com.example.yumecafeteria.data.model.ProductOrderMapper
import com.example.yumecafeteria.internal.mock.productList
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

            val existingProduct = productCartList.find { it.product.id == id }

            if (existingProduct != null) {
                existingProduct.quantity++
            } else {
                productCartList.add(ProductCart(product, 1))
            }
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

    fun getCartQuantity(): Int {

        var quantity = 0

        if (productCartList.isNotEmpty()) {
            quantity = productCartList.sumOf { it.quantity }
        }

        return quantity
    }

    fun sumTotalPrice(): Double {

        var total = 0.0

        if (productCartList.isNotEmpty()) {
            total = productCartList.sumOf { it.product.price * it.quantity }
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

        if (existingProduct != null && existingProduct!!.quantity != 1) {
            existingProduct.quantity--
        }
    }

    suspend fun insertProductsMock() {
        withContext(Dispatchers.IO) {
            productDao.save(productList)
        }
    }

    suspend fun getAllProducts(): List<ProductEntity> {
        return withContext(Dispatchers.IO) {
            productDao.getAll()
        }
    }

    suspend fun createOrder() {
        withContext(Dispatchers.IO) {
            val orderId = ordersDao.save(OrderEntity())
            productCartList.forEach {
                productOrderDao.save(ProductOrderMapper.map(it, orderId))
            }
            productCartList.clear()
        }
    }

    suspend fun getAllOrders(): List<Order> {
        return withContext(Dispatchers.IO) {
            // cria uma lista de pedidos
            val orderList = mutableListOf<Order>()

            // pega a lista de pedidos
            val orderListFromDao = ordersDao.getAll()

            // passa por todos os pedidos
            orderListFromDao.forEach { order ->
                var totalProductsCount = 0
                var totalPrice = 0.0
                val productList = mutableListOf<ProductOrder>()

                // pega a lista de produtos de um determindado pedido
                val productListFromDao = productOrderDao.getAllByOrder(order.id)

                //pra cada produto desse, faz o map pra um ProductOrder
                // e adiciona na lista de ProductOrder
                productListFromDao.forEach { productOrder ->
                    val product = productDao.searchId(productOrder.productId)
                    totalProductsCount += productOrder.quantity
                    totalPrice += product.price * productOrder.quantity

                    productList.add(ProductOrder(product, productOrder.quantity))
                }

                // adiciona a lista mapeada na lista de pedidos
                orderList.add(Order(order.id, productList, totalProductsCount, totalPrice))
            }

            return@withContext orderList
        }
    }
}
