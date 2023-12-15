package com.example.yumecafeteria.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumecafeteria.domain.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val repository: MenuRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<MenuState>()

    fun loadProductsList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(MenuState.Loading)
            delay(3000)

            try {
                val characterList = withContext(Dispatchers.IO) {
                    repository.loadProducts()
                }

                loadStateLiveData.postValue(MenuState.Success(characterList))
            } catch (error: Exception) {
                loadStateLiveData.postValue(MenuState.Error(error.message))
            }
        }
    }
}
