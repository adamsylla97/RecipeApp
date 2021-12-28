package com.recipeapp.ui.categories.recipedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.recipeapp.ui.categories.api.model.Hit
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailsViewModel(
    private val recipeId: String,
    private val service: RecipesService
) : ViewModel() {

    val recipeData = liveData(viewModelScope.coroutineContext) {
        emit(load())
    }

    suspend fun load() = withContext(Dispatchers.IO) {
        val recipe = service.getRecipe(recipeId)
        Log.i("supertest123", recipe.toString())
//        Log.i("supertest123", recipe.source)
        recipe
    }

}