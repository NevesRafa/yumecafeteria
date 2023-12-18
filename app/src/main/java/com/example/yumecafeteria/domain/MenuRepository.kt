package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.Product

class MenuRepository(private val database: ProductDao) {

    fun insertProducts() {
        database.save(productList)
    }

    fun getAllProducts(): List<Product> {
        return database.getAll()
    }
}