package com.recipeapp.ui.mydishes.adddish

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.recipeapp.R
import com.recipeapp.databinding.FragmentAddDishBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class AddDishFragment : Fragment() {

    private var _binding: FragmentAddDishBinding? = null
    private val binding get() = _binding!!

    private val REQUEST_IMAGE_CAPTURE = 1
    private val viewModel: AddDishViewModel by viewModel()
    private lateinit var photoPath: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddDishBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            viewModel.addDish()
            findNavController().popBackStack()
        }
        binding.makePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }
        viewModel.photoUrl.observe(viewLifecycleOwner) {
            binding.addDishImage.setImageURI(Uri.parse(it))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            viewModel.photoUrl.value = photoPath
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var photoFile: File? = null
        try {
            if(takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
                photoFile = createImageFile()
            }
        } catch (e: Exception) {}
        if(photoFile != null) {
            val photoUri = FileProvider.getUriForFile(
                requireContext(),
                "com.recipeapp.android.fileprovider",
                photoFile
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

    }

    private fun createImageFile(): File? {
        val fileName = System.currentTimeMillis().toString()
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )

        photoPath = image.absolutePath
        return image
    }

}