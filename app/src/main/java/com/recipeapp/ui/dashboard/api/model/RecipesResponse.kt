package com.recipeapp.ui.dashboard.api.model

data class RecipesResponse(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)