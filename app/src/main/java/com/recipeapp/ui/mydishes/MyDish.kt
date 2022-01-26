package com.recipeapp.ui.mydishes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MyDish(
    val name: String,
    val ingredients: List<MyIngredient>
)

data class MyIngredient(
    val name: String,
    val amount: String
) {
    override fun toString(): String {
        return "$amount of $name"
    }
}

@Entity
data class MyDishEntity(
    @ColumnInfo(name = "myDishName") val name: String,
    @ColumnInfo(name = "myDishIngredients") val ingredients: String,
    @PrimaryKey(autoGenerate = true) val myDishId: Int? = null
) {

    fun from(): MyDish {
        val ingredientsList = mutableListOf<MyIngredient>()
        val ingredientsParts = ingredients.split("/")
        ingredientsParts.forEach {
            val ingredient = it.split("_")
            ingredientsList.add(MyIngredient(ingredient[0], ingredient[1]))
        }
        return MyDish(name, ingredientsList)
    }

    companion object {
        fun create(myDish: MyDish): MyDishEntity {
            val ingredientsList = StringBuilder()
            myDish.ingredients.forEach {
                ingredientsList.append(it.name).append("_").append(it.amount).append("/")
            }
            return MyDishEntity(myDish.name, ingredientsList.toString())
        }
    }

}