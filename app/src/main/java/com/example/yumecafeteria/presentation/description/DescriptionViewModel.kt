package com.example.yumecafeteria.presentation.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.domain.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DescriptionViewModel(private val repository: OrderRepository) : ViewModel() {

    fun addProductCart(id: Long) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.addProductToCartById(id)

        }
    }
}