package com.recipeapp.ui.categories.recipedetails

import android.graphics.drawable.Drawable
import android.view.MenuItem
import androidx.lifecycle.*
import com.recipeapp.R
import com.recipeapp.commons.ResourcesProvider
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailsViewModel(
    private val recipeId: String,
    private val service: RecipesService,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val recipeData = liveData(viewModelScope.coroutineContext) {
        emit(load())
    }

    fun onFavoriteButtonClick(menuItem: MenuItem) = viewModelScope.launch {
        val isFavorite = service.isFavorite(recipeId)
        if (isFavorite) {
            removeRecipeFromFavorites()
        } else {
            addRecipeToFavorites()
        }
        updateFavoriteIcon(menuItem)
    }

    fun init(menuItem: MenuItem) = viewModelScope.launch {
        updateFavoriteIcon(menuItem)
    }

    private suspend fun addRecipeToFavorites() = withContext(Dispatchers.IO) {
        service.addRecipeToFavorites(recipeId)
        service.getAllRecipesInDB()
    }

    private suspend fun removeRecipeFromFavorites() = withContext(Dispatchers.IO) {
        service.removeRecipeFromFavorites(recipeId)
        service.getAllRecipesInDB()

    }

    private suspend fun updateFavoriteIcon(menuItem: MenuItem) {
        val isFavorite = service.isFavorite(recipeId)
        withContext(Dispatchers.Main) {
            val icon = if (isFavorite) {
                resourcesProvider.getDrawable(R.drawable.ic_full_star)
            } else {
                resourcesProvider.getDrawable(R.drawable.ic_empty_star)
            }
            menuItem.icon = icon
        }
    }

    private suspend fun load() = withContext(Dispatchers.IO) {
        service.getRecipe(recipeId)
    }

}