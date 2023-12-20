package com.example.yumecafeteria.presentation.cart


import com.example.yumecafeteria.data.model.ProductCart

sealed class CartState {

    data object Loading : CartState()

    data class Remove(val product: ProductCart) : CartState()

    data class UpdateTotalQuantity(val total: Int) : CartState()

    data class TotalPrice(val total: Double) : CartState()

    data class Success(val result: List<ProductCart>) : CartState()

    data class Error(val errorMessage: String?) : CartState()

}