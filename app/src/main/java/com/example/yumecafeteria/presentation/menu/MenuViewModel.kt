package com.example.yumecafeteria.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.repository.OrderRepository
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: OrderRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<MenuState>()

    fun loadProductsList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(MenuState.Loading)

            try {
                val productList = repository.getAllProducts()
                val cartQuantity = repository.getCartQuantity()

                loadStateLiveData.postValue(MenuState.Success(productList, cartQuantity))
            } catch (error: Exception) {
                loadStateLiveData.postValue(MenuState.Error(error.message))
            }
        }
    }
}
