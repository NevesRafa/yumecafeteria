package com.example.yumecafeteria.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.data.model.Product
import com.example.yumecafeteria.domain.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<CartState>()

    fun getProductCartList() {
        viewModelScope.launch {

            val cartProductList = repository.getProductCartList()

            loadStateLiveData.postValue(CartState.Success(cartProductList))
        }
    }

    fun removeProduct(product: Product) {
        viewModelScope.launch {
            repository.removeProduct(product)
            getProductCartList()
        }
    }

}