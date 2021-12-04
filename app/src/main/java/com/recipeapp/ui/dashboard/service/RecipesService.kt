package com.recipeapp.ui.dashboard.service

import android.util.Log
import com.recipeapp.ui.dashboard.api.RecipesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesService(private val api: RecipesApi) {

    suspend fun getAllRecipes() = withContext(Dispatchers.IO) {
        val response = api.getAllRecipes("chicken")
        Log.i("supertest123", "1")
        Log.i("supertest123", response.toString())
        response.hits.forEach {
            Log.i("supertest123", it.recipe.label)
        }
    }

}