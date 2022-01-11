package com.recipeapp.ui.categories.recipedetails.sharerecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeQrCodeDialogBinding
import com.recipeapp.ui.categories.recipedetails.RecipeDetailsViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RecipeQrCodeDialog : DialogFragment() {

    private val viewModel: RecipeDetailsViewModel by koinNavGraphViewModel(R.id.recipe_details_navigation)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentRecipeQrCodeDialogBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

}