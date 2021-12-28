package com.recipeapp

import androidx.room.Room
import com.recipeapp.api.RestServiceBuilder
import com.recipeapp.commons.AndroidResourcesProvider
import com.recipeapp.commons.ResourcesProvider
import com.recipeapp.database.FavoriteRecipesDataBase
import com.recipeapp.ui.categories.RecipeCategoriesViewModel
import com.recipeapp.ui.categories.api.RecipesApi
import com.recipeapp.ui.categories.categorydetails.CategoryDetailsViewModel
import com.recipeapp.ui.categories.recipedetails.RecipeDetailsViewModel
import com.recipeapp.ui.categories.service.RecipesService
import com.recipeapp.ui.favorites.FavoritesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    private const val DATABASE_NAME = "favorites_recipes_database"

    val main = module {

        single {
            val db: FavoriteRecipesDataBase = Room.databaseBuilder(
                androidContext(),
                FavoriteRecipesDataBase::class.java,
                DATABASE_NAME
            ).build()
            db
        }

        viewModel {
            val resourcesProvider: ResourcesProvider = get()
            RecipeCategoriesViewModel(resourcesProvider)
        }

        single {
            val api = RestServiceBuilder.build(RecipesApi::class.java)
            val db: FavoriteRecipesDataBase = get()
            RecipesService(api, db)
        }

        viewModel { (categoryName: String) ->
            val service: RecipesService = get()
            CategoryDetailsViewModel(categoryName, service)
        }

        viewModel { (recipeId: String) ->
            val service: RecipesService = get()
            val resourcesProvider: ResourcesProvider = get()
            RecipeDetailsViewModel(recipeId, service, resourcesProvider)
        }

    }

    val common = module {

        single<ResourcesProvider> {
            AndroidResourcesProvider(androidContext())
        }

    }

    val favorites = module {
        viewModel {
            val service: RecipesService = get()
            FavoritesViewModel(service)
        }
    }

}