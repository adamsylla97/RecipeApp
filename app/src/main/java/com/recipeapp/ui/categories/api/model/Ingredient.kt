package com.recipeapp.ui.categories.api.model

data class Ingredient(
    val food: String,
    val foodId: String,
    val measure: String,
    val quantity: Double,
    val text: String,
    val weight: Double
)