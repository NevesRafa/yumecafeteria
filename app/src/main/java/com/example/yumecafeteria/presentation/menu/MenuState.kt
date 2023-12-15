package com.example.yumecafeteria.presentation.menu

import com.example.yumecafeteria.data.model.Product

sealed class MenuState {

    data object Loading : MenuState()

    data class Success(val result: List<Product>) : MenuState()

    data class Error(val errorMessage: String?) : MenuState()

}