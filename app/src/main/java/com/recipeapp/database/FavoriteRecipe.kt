package com.recipeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteRecipe(
    @ColumnInfo(name = "recipeId") val recipeId: String,
    @PrimaryKey(autoGenerate = true) val rid: Int? = null
)