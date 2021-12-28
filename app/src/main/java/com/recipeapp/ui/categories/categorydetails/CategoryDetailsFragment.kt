package com.recipeapp.ui.categories.categorydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipeapp.R
import com.recipeapp.api.RestServiceBuilder
import com.recipeapp.databinding.FragmentCategoryDetailsBinding
import com.recipeapp.databinding.FragmentRecipeCategoriesBinding
import com.recipeapp.ui.categories.api.RecipesApi
import com.recipeapp.ui.categories.service.RecipesService
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryDetailsFragment : Fragment() {

    private val navArgs: CategoryDetailsFragmentArgs by navArgs()
    private val viewModel: CategoryDetailsViewModel by viewModel { parametersOf(navArgs.categoryName) }

    private var _binding: FragmentCategoryDetailsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCategoryDetailsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipes = binding.recipes
        recipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoryDetailsAdapter { name, id ->
                onItemClick(name, id)
            }
        }

    }

    private fun onItemClick(recipeName: String, recipeId: String) {
        findNavController().navigate(CategoryDetailsFragmentDirections.toRecipeDetails(recipeName, recipeId))
    }

}