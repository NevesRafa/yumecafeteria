package com.example.yumecafeteria.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.domain.CartRepository
import com.example.yumecafeteria.domain.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(
    private val repository: MenuRepository, private val cartRepository: CartRepository
) : ViewModel() {

    val loadStateLiveData = MutableLiveData<MenuState>()

    val cartList = cartRepository.getProductCartList()

    fun loadProductsList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(MenuState.Loading)

            try {
                val characterList = withContext(Dispatchers.IO) {
                    repository.getAllProducts()
                }

                loadStateLiveData.postValue(MenuState.Success(characterList))
            } catch (error: Exception) {
                loadStateLiveData.postValue(MenuState.Error(error.message))
            }
        }
    }

    fun saveListProduct() {
        viewModelScope.launch {
            repository.insertProducts()
        }
    }
}
