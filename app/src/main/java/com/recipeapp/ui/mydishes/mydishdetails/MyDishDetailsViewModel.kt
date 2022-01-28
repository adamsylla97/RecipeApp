package com.recipeapp.ui.mydishes.mydishdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.mydishes.MyDish
import com.recipeapp.ui.mydishes.MyDishesService

class MyDishDetailsViewModel(
    private val dishId: Int,
    private val service: MyDishesService
) : ViewModel() {

    val myDish: LiveData<MyDish> = liveData(viewModelScope.coroutineContext) {
        emit(getDish())
    }

    private suspend fun getDish() = service.getDish(dishId)

}