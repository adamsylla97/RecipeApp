package com.recipeapp.ui.mydishes.adddish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.mydishes.MyDish
import com.recipeapp.ui.mydishes.MyDishesService
import kotlinx.coroutines.launch

class AddDishViewModel(private val service: MyDishesService): ViewModel() {

    val recipeName = MutableLiveData<String>()
    val instructions = MutableLiveData<String>()
    val ingredients = MutableLiveData<String>()

    fun addDish() = viewModelScope.launch {
        service.addDish(MyDish(
            name = recipeName.value!!,
            instructions = instructions.value!!,
            ingredients = ingredients.value!!
        ))
    }

}