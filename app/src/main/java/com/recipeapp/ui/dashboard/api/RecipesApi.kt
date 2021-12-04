package com.recipeapp.ui.dashboard.api

import com.recipeapp.api.Constants
import com.recipeapp.ui.dashboard.api.model.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("/api/recipes/v2")
    suspend fun getAllRecipes(
        @Query("q") q: String,
        @Query("type") type: String = "public",
        @Query("app_id") app_id: String = Constants.APPLICATION_ID,
        @Query("app_key") app_key: String = Constants.APPLICATION_KEY
    ): RecipesResponse

}