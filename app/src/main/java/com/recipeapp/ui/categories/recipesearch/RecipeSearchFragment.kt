package com.recipeapp.ui.categories.recipesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeSearchBinding
import com.recipeapp.ui.categories.categorydetails.CategoryDetailsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeSearchFragment : Fragment() {

    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipeSearchViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecipeSearchBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoryDetailsAdapter { name, id ->
                onItemClick(name, id)
            }
        }

    }

    private fun onItemClick(name: String, id: String) {
        findNavController().navigate(RecipeSearchFragmentDirections.toRecipeDetails(name, id))
    }

}