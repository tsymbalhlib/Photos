package com.example.android.photos.photos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.photos.R
import com.example.android.photos.databinding.PhotosFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.photos_fragment) {

    private val photosViewModel: PhotosViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PhotosFragmentBinding.bind(view)

        binding.photosViewModel = photosViewModel

        binding.lifecycleOwner = this

        val adapter = PhotosAdapter(PhotoListener { photo ->
            photosViewModel.onNavigateToPhotoDetail(photo)
        })

        binding.photosList.adapter = adapter

        photosViewModel.navigateToPhotoDetail.observe(viewLifecycleOwner, { photoEvent ->
            photoEvent.getContentIfNotHandled()?.let { photo ->
                findNavController().navigate(
                    PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(photo)
                )
            }
        })

        photosViewModel.showSnackBarEventValue.observe(viewLifecycleOwner, { messageEvent ->
            messageEvent.getContentIfNotHandled()?.let { message ->
                Snackbar.make(
                    requireView(),
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }
}