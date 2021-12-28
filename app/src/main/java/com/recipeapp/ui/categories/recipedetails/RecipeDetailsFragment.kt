package com.recipeapp.ui.categories.recipedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeDetailsBinding
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RecipeDetailsFragment : Fragment() {

    private val navArgs: RecipeDetailsFragmentArgs by navArgs()
    private val viewModel: RecipeDetailsViewModel by viewModel{ parametersOf(navArgs.recipeId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentRecipeDetailsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }.root
    }

}