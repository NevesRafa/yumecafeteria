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

    fun getProductCartList() {
        viewModelScope.launch(Dispatchers.IO) {
            val cartProductList = repository.getProductsInCart()
            val totalQuantity = repository.sumQuantity()
            val totalPrice = repository.sumPrice()
            loadStateLiveData.postValue(CartState.Success(cartProductList, totalQuantity, totalPrice))
        }
    }

    fun removeProduct(product: ProductCart) {
        viewModelScope.launch {
            repository.removeProductFromCart(product)
            getProductCartList()
        }
    }
}