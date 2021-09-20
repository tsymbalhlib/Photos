package com.example.android.photos.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.photos.R
import com.example.android.photos.databinding.PhotoDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment(R.layout.photo_detail_fragment) {

    private val photoDetailViewModel: PhotoDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = PhotoDetailFragmentBinding.bind(view)

        binding.photoDetailViewModel = photoDetailViewModel

        binding.lifecycleOwner = this

        photoDetailViewModel.currentUrl.observe(viewLifecycleOwner, { eventUrl ->
            eventUrl.getContentIfNotHandled()?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        })
    }
}