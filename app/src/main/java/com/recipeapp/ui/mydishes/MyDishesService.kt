package com.recipeapp.ui.mydishes

import com.recipeapp.database.FavoriteRecipesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MyDishesService(private val db: FavoriteRecipesDataBase) {

    suspend fun addDish(myDish: MyDish) = withContext(Dispatchers.IO) {
        db.myDishDao().insertMyDish(MyDishEntity.create(myDish))
    }

    suspend fun getAllDishes(): Flow<List<MyDish>> = withContext(Dispatchers.IO) {
        db.myDishDao().getAllMyDishes().map { list ->
            list.map { myDishEntity -> myDishEntity.from() }.toList()
        }
    }

    suspend fun getDish(dishId: Int) = withContext(Dispatchers.IO) {
        db.myDishDao().getMyDish(dishId).from()
    }

}