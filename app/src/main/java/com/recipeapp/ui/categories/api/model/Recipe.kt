package com.recipeapp.ui.categories.api.model

data class Recipe(
    val calories: Double,
    val cautions: List<String>,
    val co2EmissionsClass: String,
    val cuisineType: List<String>,
    val dietLabels: List<String>,
    val digest: List<Digest>,
    val dishType: List<String>,
    val glycemicIndex: Double,
    val healthLabels: List<String>,
    val image: String,
    val images: Images,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val label: String,
    val mealType: List<String>,
    val shareAs: String,
    val source: String,
    val totalCO2Emissions: Double,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalWeight: Double,
    val uri: String,
    val url: String,
    val yield: Double
) {
    fun getId() = uri.split("#recipe_")[1]
    fun getIngredientsText(): String {
        val ingredients = StringBuilder()
        ingredientLines.forEach {
            ingredients.append(it).append("\n")
        }
        return ingredients.toString()
    }

}