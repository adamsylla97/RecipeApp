package com.recipeapp.ui.categories.recipedetails.model

data class Recipe(
    val calories: Int,
    val cautions: List<String>,
    val co2EmissionsClass: String,
    val cuisineType: List<String>,
    val dietLabels: List<String>,
    val digest: List<Digest>,
    val dishType: List<String>,
    val glycemicIndex: Int,
    val healthLabels: List<String>,
    val image: String,
    val images: Images,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val label: String,
    val mealType: List<String>,
    val shareAs: String,
    val source: String,
    val totalCO2Emissions: Int,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalWeight: Int,
    val uri: String,
    val url: String,
    val yield: Int
)