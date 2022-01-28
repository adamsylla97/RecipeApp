package com.recipeapp.ui.mydishes

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyDishesViewModel(private val service: MyDishesService) : ViewModel() {

    private val _myDishes: MutableStateFlow<List<MyDish>> = MutableStateFlow(emptyList())
    val myDishes: StateFlow<List<MyDish>> = _myDishes

    init {
        viewModelScope.launch {
            service.getAllDishes().collect { dishes ->
                _myDishes.value = dishes
            }
        }
    }

}