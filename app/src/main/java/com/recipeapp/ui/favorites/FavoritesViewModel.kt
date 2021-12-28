package com.recipeapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.categories.categorydetails.RecipeItem
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.launch

class FavoritesViewModel(private val service: RecipesService) : ViewModel() {

    private val _favoriteRecipes: MutableLiveData<List<RecipeItem>> = MutableLiveData(emptyList())
    val favoriteRecipes: LiveData<List<RecipeItem>> = _favoriteRecipes

    fun load() = viewModelScope.launch {
        _favoriteRecipes.value = service.getAllFavoriteRecipes()
    }

}