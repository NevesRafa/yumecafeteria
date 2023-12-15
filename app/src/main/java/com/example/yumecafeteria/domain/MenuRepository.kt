package com.example.yumecafeteria.domain

import com.example.yumecafeteria.data.model.Product

class MenuRepository {

    fun loadProducts(): List<Product> {

        return productList
    }
}