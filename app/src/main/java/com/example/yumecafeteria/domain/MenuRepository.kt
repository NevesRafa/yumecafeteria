package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.local.dao.ProductDao
import com.example.yumecafeteria.data.model.Product

class MenuRepository(private val productDao: ProductDao) {


    fun insertProducts() {
        productDao.save(productList)
    }

    fun getAllProducts(): List<Product> {
        return productDao.getAll()
    }
}