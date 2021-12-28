package com.recipeapp.ui.categories.categorydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.launch

class CategoryDetailsViewModel(private val category: String, private val service: RecipesService): ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeItem>>().apply { value = emptyList() }
    val recipes: LiveData<List<RecipeItem>> = _recipes

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() = viewModelScope.launch {
        _recipes.value = service.getAllRecipes(category)
    }

}