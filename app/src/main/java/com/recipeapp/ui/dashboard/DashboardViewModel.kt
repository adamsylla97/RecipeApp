package com.recipeapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.dashboard.service.RecipesService
import kotlinx.coroutines.launch

class DashboardViewModel(private val service: RecipesService) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() = viewModelScope.launch {
        service.getAllRecipes()
    }

}