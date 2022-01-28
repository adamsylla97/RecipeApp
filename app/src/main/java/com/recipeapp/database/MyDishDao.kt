package com.recipeapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.recipeapp.ui.mydishes.MyDishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDishDao {

    @Query("SELECT * FROM MyDishEntity")
    fun getAllMyDishes(): Flow<List<MyDishEntity>>

    @Insert
    fun insertMyDish(myDishEntity: MyDishEntity)

    @Delete
    fun deleteMyDish(myDishEntity: MyDishEntity)

    @Query("SELECT * FROM MyDishEntity WHERE :dishId == myDishId")
    fun getMyDish(dishId: Int): MyDishEntity

}