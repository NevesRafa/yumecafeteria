package com.example.yumecafeteria.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.domain.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<CartState>()

    private val cartProductList = repository.getProductsInCart()

    fun getProductCartList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(CartState.Success(cartProductList))

        }
    }

    fun removeProduct(product: ProductCart) {
        viewModelScope.launch {
            repository.removeProductFromCart(product)
            getProductCartList()

        }
    }

    fun updateQuantityTotal() {
        viewModelScope.launch(Dispatchers.IO) {
            val quantity = repository.sumQuantity()
            loadStateLiveData.postValue(CartState.UpdateTotalQuantity(quantity))
            getProductCartList()
        }
    }

    fun updateTotal() {
        viewModelScope.launch(Dispatchers.IO) {
            val total = repository.sumPrice()
            loadStateLiveData.postValue(CartState.TotalPrice(total))
            getProductCartList()
        }
    }

}