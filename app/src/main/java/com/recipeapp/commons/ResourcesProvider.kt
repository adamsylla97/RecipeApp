package com.recipeapp.commons

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

interface ResourcesProvider {

    fun getString(id: Int): String
    fun getDrawable(id: Int): Drawable?

}

class AndroidResourcesProvider(private val context: Context): ResourcesProvider {

    override fun getString(@StringRes id: Int) = context.resources.getString(id)

    override fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

}