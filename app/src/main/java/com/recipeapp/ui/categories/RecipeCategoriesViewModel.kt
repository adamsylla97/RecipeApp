package com.recipeapp.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipeapp.R
import com.recipeapp.commons.ResourcesProvider
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.launch

class RecipeCategoriesViewModel(private val resourcesProvider: ResourcesProvider) : ViewModel() {

    private val preparedCategories = listOf(
        RecipeCategoryItem("Chicken", resourcesProvider.getDrawable(R.drawable.chicken)),
        RecipeCategoryItem("Pasta", resourcesProvider.getDrawable(R.drawable.pasta)),
        RecipeCategoryItem("Pizza", resourcesProvider.getDrawable(R.drawable.pizza)),
        RecipeCategoryItem("Fish", resourcesProvider.getDrawable(R.drawable.fish)),
        RecipeCategoryItem("Vegetarian", resourcesProvider.getDrawable(R.drawable.wege)),
        RecipeCategoryItem("Beef", resourcesProvider.getDrawable(R.drawable.beef)),
        RecipeCategoryItem("Soup", resourcesProvider.getDrawable(R.drawable.soup)),
        RecipeCategoryItem("Burger", resourcesProvider.getDrawable(R.drawable.burger)),
        RecipeCategoryItem("Salad", resourcesProvider.getDrawable(R.drawable.salad)),
        RecipeCategoryItem("Kebab", resourcesProvider.getDrawable(R.drawable.kebab)),
        RecipeCategoryItem("Pancakes", resourcesProvider.getDrawable(R.drawable.pancakes)),
        RecipeCategoryItem("Sushi", resourcesProvider.getDrawable(R.drawable.sushi)),
        RecipeCategoryItem("Dessert", resourcesProvider.getDrawable(R.drawable.dessert)),
        RecipeCategoryItem("Chinese", resourcesProvider.getDrawable(R.drawable.chinese))
    )
    private val _recipes = MutableLiveData<List<RecipeCategoryItem>>().apply { value = emptyList() }
    val recipes: LiveData<List<RecipeCategoryItem>> = _recipes

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() = viewModelScope.launch {
        _recipes.value = preparedCategories
    }

}