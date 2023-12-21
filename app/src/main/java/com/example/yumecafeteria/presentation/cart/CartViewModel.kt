package com.example.yumecafeteria.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.data.model.ProductCart
import com.example.yumecafeteria.domain.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val repository: OrderRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<CartState>()

    fun getProductCartList() {
        viewModelScope.launch(Dispatchers.IO) {
            val cartProductList = repository.getProductsInCart()
            val totalQuantity = repository.getCartQuantity()
            val totalPrice = repository.sumTotalPrice()
            loadStateLiveData.postValue(CartState.Success(cartProductList, totalQuantity, totalPrice))
        }
    }

    fun removeProduct(product: ProductCart) {
        viewModelScope.launch {
            repository.removeProductFromCart(product)
            getProductCartList()
        }
    }

    fun createOrder() {
        viewModelScope.launch {
            repository.createOrder()
        }
    }

    fun increaseQuantity(productId: Long) {
        viewModelScope.launch {
            repository.increaseQuantity(productId)
            getProductCartList()
        }
    }

    fun decreaseQuantity(productId: Long) {
        viewModelScope.launch {
            repository.decreaseQuantity(productId)
            getProductCartList()
        }
    }
}