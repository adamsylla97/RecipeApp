package com.recipeapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipeapp.databinding.FragmentFavoritesBinding
import com.recipeapp.ui.categories.categorydetails.CategoryDetailsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding =  FragmentFavoritesBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = favoritesViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel.load()
        binding.favoritesRecipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoryDetailsAdapter { name, id ->
                onItemClick(name, id)
            }
        }

    }

    private fun onItemClick(recipeName: String, recipeId: String) {
        findNavController().navigate(FavoritesFragmentDirections.toRecipeDetails(recipeName, recipeId))
    }

}