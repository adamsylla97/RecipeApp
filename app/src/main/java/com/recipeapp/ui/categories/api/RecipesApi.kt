package com.recipeapp.ui.categories.api

import com.recipeapp.api.Constants
import com.recipeapp.ui.categories.api.model.Hit
import com.recipeapp.ui.categories.api.model.Recipe
import com.recipeapp.ui.categories.api.model.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("/api/recipes/v2")
    suspend fun getAllRecipes(
        @Query("q") q: String,
        @Query("type") type: String = "public",
        @Query("app_id") app_id: String = Constants.APPLICATION_ID,
        @Query("app_key") app_key: String = Constants.APPLICATION_KEY
    ): RecipesResponse

    @GET("/api/recipes/v2/{id}")
    suspend fun getRecipe(
        @Path("id") recipeId: String,
        @Query("type") type: String = "public",
        @Query("app_id") app_id: String = Constants.APPLICATION_ID,
        @Query("app_key") app_key: String = Constants.APPLICATION_KEY
    ): Hit

}