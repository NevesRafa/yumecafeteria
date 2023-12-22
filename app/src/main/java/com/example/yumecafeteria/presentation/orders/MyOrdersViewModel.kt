package com.example.yumecafeteria.presentation.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyOrdersViewModel(private val repository: OrderRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<MyOrdersState>()

    fun getOrderList() {
        viewModelScope.launch(Dispatchers.IO) {
            loadStateLiveData.postValue(MyOrdersState.Loading)

            try {
                val orderList = withContext(Dispatchers.IO) {
                    repository.getAllOrders()
                }

                loadStateLiveData.postValue(MyOrdersState.Success(orderList))
            } catch (error: Exception) {
                loadStateLiveData.postValue(MyOrdersState.Error(error.message))
            }
        }
    }
}