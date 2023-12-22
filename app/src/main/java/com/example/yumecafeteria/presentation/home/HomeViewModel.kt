package com.example.yumecafeteria.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.repository.OrderRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: OrderRepository) : ViewModel() {

    private fun saveMockListProduct() {
        viewModelScope.launch {
            repository.insertProductsMock()
        }
    }

    fun getProductList() {
        viewModelScope.launch {
            val productList = repository.getAllProducts()

            if (productList.isEmpty()) {
                saveMockListProduct()
            }
        }
    }
}