package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.Product

class CartRepository(private val database: ProductDao) {

    val productCartList = mutableListOf<Product>()

    fun getProductById(id: Int): List<Product> {

        val product = database.searchId(id)

        productCartList.add(product)

        return productCartList
    }
}