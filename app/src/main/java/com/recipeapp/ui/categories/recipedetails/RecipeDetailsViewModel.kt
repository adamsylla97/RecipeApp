package com.recipeapp.ui.categories.recipedetails

import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.MenuItem
import androidx.lifecycle.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.recipeapp.R
import com.recipeapp.commons.ResourcesProvider
import com.recipeapp.ui.categories.service.RecipesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailsViewModel(
    private val recipeId: String,
    private val service: RecipesService
) : ViewModel() {

    val recipeData = liveData(viewModelScope.coroutineContext) {
        emit(load())
    }
    private val _qrCode = MutableLiveData<Bitmap>() //bitmap shouldnt be in viewmodel
    val qrCode: LiveData<Bitmap> = _qrCode

    fun onFavoriteButtonClick(menuItem: MenuItem) = viewModelScope.launch {
        val isFavorite = service.isFavorite(recipeId)
        if (isFavorite) {
            removeRecipeFromFavorites()
        } else {
            addRecipeToFavorites()
        }
        updateFavoriteIcon(menuItem)
    }

    fun generateQrCode() {
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(recipeData.value?.recipe?.url, BarcodeFormat.QR_CODE, 150, 150)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.createBitmap(bitMatrix)
        _qrCode.value = bitmap
    }

    fun init(menuItem: MenuItem) = viewModelScope.launch {
        updateFavoriteIcon(menuItem)
    }



    private suspend fun addRecipeToFavorites() = withContext(Dispatchers.IO) {
        service.addRecipeToFavorites(recipeId)
        service.getAllRecipesInDB()
    }

    private suspend fun removeRecipeFromFavorites() = withContext(Dispatchers.IO) {
        service.removeRecipeFromFavorites(recipeId)
        service.getAllRecipesInDB()

    }

    private suspend fun updateFavoriteIcon(menuItem: MenuItem) {
        val isFavorite = service.isFavorite(recipeId)
        withContext(Dispatchers.Main) {
            val icon = if (isFavorite) {
                R.drawable.ic_full_star
            } else {
                R.drawable.ic_empty_star
            }
            menuItem.setIcon(icon)
        }
    }

    private suspend fun load() = withContext(Dispatchers.IO) {
        service.getRecipe(recipeId)
    }

}