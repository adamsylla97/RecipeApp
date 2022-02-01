package com.recipeapp.ui.categories.recipesearch

import androidx.lifecycle.*
import com.recipeapp.ui.categories.categorydetails.RecipeItem
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.launch

class RecipeSearchViewModel(private val service: RecipesService): ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeItem>>(emptyList())
    val recipes: LiveData<List<RecipeItem>> = _recipes
    val searchQuery = MutableLiveData<String>()
    val searchEnabled = Transformations.map(searchQuery) {
        it.isNotEmpty()
    }


    fun search() = viewModelScope.launch {
        val category = searchQuery.value!!
        val fetchedRecipes: List<RecipeItem> = service.getAllRecipes(category)
        _recipes.value = fetchedRecipes
    }

}