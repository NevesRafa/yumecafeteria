package com.example.yumecafeteria.presentation.menu

import com.example.yumecafeteria.data.local.entity.ProductEntity

sealed class MenuState {

    data object Loading : MenuState()

    data class Success(
        val result: List<ProductEntity>,
        val cartQuantity: Int
    ) : MenuState()

    data class Error(val errorMessage: String?) : MenuState()

}