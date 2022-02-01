package com.recipeapp.ui.mydishes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MyDish(
    val id: Int = 0,
    val name: String,
    val instructions: String,
    val ingredients: String,
    val photoUrl: String
)

@Entity
data class MyDishEntity(
    @ColumnInfo(name = "myDishName") val name: String,
    @ColumnInfo(name = "myDishInstructions") val instructions: String,
    @ColumnInfo(name = "myDishIngredients") val ingredients: String,
    @ColumnInfo(name = "myDishPhotoUrl") val photoUrl: String,
    @PrimaryKey(autoGenerate = true) val myDishId: Int? = null
) {

    fun from(): MyDish {
        return MyDish(myDishId!!, name, instructions, ingredients, photoUrl)
    }

    companion object {
        fun create(myDish: MyDish): MyDishEntity {

            return MyDishEntity(
                name = myDish.name,
                instructions = myDish.instructions,
                ingredients = myDish.ingredients,
                photoUrl = myDish.photoUrl
            )

        }
    }

}