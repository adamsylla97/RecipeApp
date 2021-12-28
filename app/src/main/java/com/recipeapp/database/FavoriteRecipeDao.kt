package com.recipeapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteRecipeDao {

    @Query("SELECT * FROM FavoriteRecipe")
    fun getAllFavorites(): List<FavoriteRecipe>

    @Insert
    fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Delete
    fun deleteFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM FavoriteRecipe WHERE recipeId == :recipeId")
    fun getFavoriteRecipe(recipeId: String): FavoriteRecipe?

}