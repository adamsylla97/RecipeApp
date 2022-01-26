package com.recipeapp.ui.mydishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyDishesViewModel(private val service: MyDishesService) : ViewModel() {
    private val _myDishes: MutableLiveData<List<MyDish>> = MutableLiveData(emptyList())
    val myDishes: LiveData<List<MyDish>> = _myDishes

    init {
        viewModelScope.launch {
            fetchDishes()
        }
    }

    private suspend fun fetchDishes() {
        _myDishes.value = service.getAllDishes()
    }
}