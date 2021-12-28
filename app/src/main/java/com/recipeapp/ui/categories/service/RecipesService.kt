package com.recipeapp.ui.categories.service

import android.util.Log
import com.recipeapp.database.FavoriteRecipe
import com.recipeapp.database.FavoriteRecipesDataBase
import com.recipeapp.ui.categories.RecipeCategoryItem
import com.recipeapp.ui.categories.api.RecipesApi
import com.recipeapp.ui.categories.api.model.Hit
import com.recipeapp.ui.categories.api.model.Recipe
import com.recipeapp.ui.categories.api.model.RecipesResponse
import com.recipeapp.ui.categories.categorydetails.RecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class RecipesService(private val api: RecipesApi, private val db: FavoriteRecipesDataBase) {

    suspend fun getAllRecipes(category: String): List<RecipeItem> = withContext(Dispatchers.IO) {
        val response = api.getAllRecipes(category)
        toRecipes(response)
    }

    suspend fun getRecipe(recipeId: String): Hit = withContext(Dispatchers.IO) {
        api.getRecipe(recipeId)
    }

    suspend fun getAllFavoriteRecipes(): List<RecipeItem> = withContext(Dispatchers.IO) {
        val recipesInDB = db.favoritesRecipesDao().getAllFavorites()
        val recipes = mutableListOf<Hit>()
        recipesInDB.forEach {
            recipes.add(getRecipe(it.recipeId))
        }
        return@withContext recipes.map { RecipeItem(it.recipe.label, it.recipe.getId(), it.recipe.image) }
    }

    suspend fun getAllRecipesInDB() = withContext(Dispatchers.IO) {
        Log.i("supertest123", db.favoritesRecipesDao().getAllFavorites().toString())
    }

    suspend fun addRecipeToFavorites(recipeId: String) = withContext(Dispatchers.IO) {
        db.favoritesRecipesDao().insertFavoriteRecipe(FavoriteRecipe(recipeId))
    }

    suspend fun removeRecipeFromFavorites(recipeId: String) = withContext(Dispatchers.IO) {
        val recipe = db.favoritesRecipesDao().getFavoriteRecipe(recipeId)
        if(recipe != null) {
            db.favoritesRecipesDao().deleteFavoriteRecipe(recipe)
        }
    }

    suspend fun isFavorite(recipeId: String) = withContext(Dispatchers.IO) {
        return@withContext db.favoritesRecipesDao().getFavoriteRecipe(recipeId) != null
    }

    private suspend fun toRecipes(response: RecipesResponse): List<RecipeItem> = withContext(Dispatchers.IO) {
        response.hits.map {
            RecipeItem(it.recipe.label, it.recipe.getId(), it.recipe.image)
        }
    }

}