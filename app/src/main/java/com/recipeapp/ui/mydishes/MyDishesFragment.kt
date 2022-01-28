package com.recipeapp.ui.mydishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipeapp.databinding.FragmentMyDishesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyDishesFragment : Fragment() {

    private val myDishesViewModel: MyDishesViewModel by viewModel()
    private val myDishesAdapter: MyDishesAdapter by lazy { MyDishesAdapter{ dishName, dishId -> onItemClick(dishName, dishId) } }

    private var _binding: FragmentMyDishesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyDishesBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = myDishesViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myDishes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myDishesAdapter
        }
        binding.addDishButton.setOnClickListener {
            findNavController().navigate(MyDishesFragmentDirections.toAddDish())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(dishName: String, dishId: Int) {
        findNavController().navigate(MyDishesFragmentDirections.toDishDetails(dishName, dishId))
    }
}