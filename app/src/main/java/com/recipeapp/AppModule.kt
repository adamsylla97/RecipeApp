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
import com.recipeapp.ui.categories.recipesearch.RecipeSearchViewModel
import com.recipeapp.ui.categories.service.RecipesService
import com.recipeapp.ui.favorites.FavoritesViewModel
import com.recipeapp.ui.mydishes.MyDishesService
import com.recipeapp.ui.mydishes.MyDishesViewModel
import com.recipeapp.ui.mydishes.adddish.AddDishViewModel
import com.recipeapp.ui.mydishes.mydishdetails.MyDishDetailsViewModel
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
            ).fallbackToDestructiveMigration().build()
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
            RecipeDetailsViewModel(recipeId, service)
        }

        viewModel {
            val service: MyDishesService = get()
            MyDishesViewModel(service)
        }

        single {
            val db: FavoriteRecipesDataBase = get()
            MyDishesService(db)
        }

        viewModel {
            val service: RecipesService = get()
            RecipeSearchViewModel(service)
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

    val myDish = module {
        viewModel {
            val service: MyDishesService = get()
            AddDishViewModel(service)
        }
        viewModel { (dishId: Int) ->
            val service: MyDishesService = get()
            MyDishDetailsViewModel(dishId, service)
        }
    }

}