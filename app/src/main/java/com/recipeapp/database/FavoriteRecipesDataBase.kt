package com.recipeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recipeapp.ui.mydishes.MyDishEntity

@Database(entities = [FavoriteRecipe::class, MyDishEntity::class], version = 2)
abstract class FavoriteRecipesDataBase: RoomDatabase() {
    abstract fun favoritesRecipesDao(): FavoriteRecipeDao
    abstract fun myDishDao(): MyDishDao
}