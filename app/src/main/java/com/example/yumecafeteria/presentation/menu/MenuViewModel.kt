package com.example.yumecafeteria.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.domain.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val repository: OrderRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<MenuState>()

    fun loadProductsList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(MenuState.Loading)

            try {
                val productList = withContext(Dispatchers.IO) {
                    repository.getAllProducts()
                }

                loadStateLiveData.postValue(MenuState.Success(productList))
            } catch (error: Exception) {
                loadStateLiveData.postValue(MenuState.Error(error.message))
            }
        }
    }
}
