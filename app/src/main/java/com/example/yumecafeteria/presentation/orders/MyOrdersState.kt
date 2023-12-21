package com.example.yumecafeteria.presentation.orders

import com.example.yumecafeteria.data.model.Order

sealed class MyOrdersState {

    data object Loading : MyOrdersState()

    data class Success(val result: List<Order>) : MyOrdersState()

    data class Error(val errorMessage: String?) : MyOrdersState()
}