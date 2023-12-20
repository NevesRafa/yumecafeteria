package com.example.yumecafeteria.presentation.cart


import com.example.yumecafeteria.data.model.ProductCart

sealed class CartState {

    data object Loading : CartState()

    data class Success(val cartProductList: List<ProductCart>, val totalQuantity: Int, val totalPrice: Double) : CartState()

    data class Error(val errorMessage: String?) : CartState()

}