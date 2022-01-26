package com.recipeapp.ui.mydishes

import com.recipeapp.database.FavoriteRecipesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDishesService(private val db: FavoriteRecipesDataBase) {

    suspend fun addDish(myDish: MyDish) = withContext(Dispatchers.IO) {
        db.myDishDao().insertMyDish(MyDishEntity.create(myDish))
    }

    suspend fun getAllDishes(): List<MyDish> = withContext(Dispatchers.IO) {
        db.myDishDao().getAllMyDishes().map { it.from() }
    }

}