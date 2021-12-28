package com.recipeapp.ui.categories.service

import android.util.Log
import com.recipeapp.ui.categories.RecipeCategoryItem
import com.recipeapp.ui.categories.api.RecipesApi
import com.recipeapp.ui.categories.api.model.Hit
import com.recipeapp.ui.categories.api.model.Recipe
import com.recipeapp.ui.categories.api.model.RecipesResponse
import com.recipeapp.ui.categories.categorydetails.RecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesService(private val api: RecipesApi) {

    suspend fun getAllRecipes(category: String): List<RecipeItem> = withContext(Dispatchers.IO) {
        val response = api.getAllRecipes(category)
        toRecipes(response)
    }

    suspend fun getRecipe(recipeId: String): Hit = withContext(Dispatchers.IO) {
        api.getRecipe(recipeId)
    }

    private suspend fun toRecipes(response: RecipesResponse): List<RecipeItem> = withContext(Dispatchers.IO) {
        response.hits.map {
            Log.i("supertest123", "image url: ${it.recipe.image}")
            Log.i("supertest123", "recipe uri: ${it.recipe.uri}")
            Log.i("supertest123", "recipe id: ${it.recipe.getId()}")
            RecipeItem(it.recipe.label, it.recipe.getId(), it.recipe.image)
        }
    }

}