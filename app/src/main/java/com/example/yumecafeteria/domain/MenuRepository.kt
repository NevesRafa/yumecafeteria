package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepository(private val database: ProductDao) {

    suspend fun insertProducts() {
        withContext(Dispatchers.IO) {
            database.save(productList)
        }

    }

    suspend fun getAllProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            database.getAll()
        }
    }
}