package com.example.yumecafeteria.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.domain.OrderRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: OrderRepository) : ViewModel() {

    fun saveMockListProduct() {
        viewModelScope.launch {
            repository.insertProducts()
        }
    }

    fun getProductList(): List<Product> {

        var productList = listOf<Product>()

        viewModelScope.launch {
            productList = repository.getAllProducts()
        }
        return productList
    }
}