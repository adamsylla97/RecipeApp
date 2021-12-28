package com.recipeapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.recipeapp.R
import com.recipeapp.databinding.FragmentRecipeCategoriesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeCategoriesFragment : Fragment() {

    private val recipeCategoriesViewModel: RecipeCategoriesViewModel by viewModel()

    private var _binding: FragmentRecipeCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecipeCategoriesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RecipeCategoriesFragment
            viewModel = recipeCategoriesViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipes.apply {
            adapter = RecipeCategoriesAdapter{ onItemClick(it) }
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(title: String) {
        findNavController().navigate(RecipeCategoriesFragmentDirections.toDetails(title))
    }
}