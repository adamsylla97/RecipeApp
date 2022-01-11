package com.recipeapp.ui.categories.recipedetails.recipeinstructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.recipeapp.databinding.FragmentRecipeInstructionsBinding

class RecipeInstructionsFragment : Fragment() {

    private var _binding: FragmentRecipeInstructionsBinding? = null
    private val binding get() = _binding!!
    private val navArgs: RecipeInstructionsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRecipeInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipeInstructions.loadUrl(navArgs.url)
        findNavController().popBackStack()
    }

}