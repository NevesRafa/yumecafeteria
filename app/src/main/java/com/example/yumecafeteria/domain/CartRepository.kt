package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.Product

class CartRepository(private val database: ProductDao) {

    private val productCartList = mutableListOf<Product>()

    fun getProductById(id: Int): List<Product> {

        val product = database.searchId(id)

        productCartList.add(product)

        return productCartList
    }

    fun getProductCartList(): List<Product> {
        return productCartList
    }

    fun removeProduct(product: Product) {
        if (productCartList.contains(product)) {
            productCartList.remove(product)
        }
    }

}