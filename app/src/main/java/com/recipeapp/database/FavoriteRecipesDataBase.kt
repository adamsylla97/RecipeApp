package com.recipeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRecipe::class], version = 1)
abstract class FavoriteRecipesDataBase: RoomDatabase() {
    abstract fun favoritesRecipesDao(): FavoriteRecipeDao
}