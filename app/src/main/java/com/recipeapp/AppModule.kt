package com.recipeapp

import com.recipeapp.api.RestServiceBuilder
import com.recipeapp.commons.AndroidResourcesProvider
import com.recipeapp.commons.ResourcesProvider
import com.recipeapp.ui.categories.RecipeCategoriesViewModel
import com.recipeapp.ui.categories.api.RecipesApi
import com.recipeapp.ui.categories.categorydetails.CategoryDetailsViewModel
import com.recipeapp.ui.categories.recipedetails.RecipeDetailsViewModel
import com.recipeapp.ui.categories.service.RecipesService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val main = module {

        viewModel {
            val resourcesProvider: ResourcesProvider = get()
            RecipeCategoriesViewModel(resourcesProvider)
        }

        single {
            val api = RestServiceBuilder.build(RecipesApi::class.java)
            RecipesService(api)
        }

        viewModel { (categoryName: String) ->
            val service: RecipesService = get()
            CategoryDetailsViewModel(categoryName, service)
        }

        viewModel { (recipeId: String) ->
            val service: RecipesService = get()
            RecipeDetailsViewModel(recipeId, service)
        }

    }

    val common = module {

        single<ResourcesProvider> {
            AndroidResourcesProvider(androidContext())
        }

    }

}