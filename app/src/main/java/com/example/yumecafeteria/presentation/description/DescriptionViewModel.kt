package com.example.yumecafeteria.presentation.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.domain.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DescriptionViewModel(private val repository: CartRepository) : ViewModel() {

    fun addProductCart(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.addProductToCartById(id)

        }
    }
}