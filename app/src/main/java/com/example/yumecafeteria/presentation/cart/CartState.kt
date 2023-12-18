package com.example.yumecafeteria.presentation.cart

import com.example.yumecafeteria.data.model.Product

sealed class CartState {

    data object Loading : CartState()

    data class Success(val result: List<Product>) : CartState()

    data class Error(val errorMessage: String?) : CartState()

}