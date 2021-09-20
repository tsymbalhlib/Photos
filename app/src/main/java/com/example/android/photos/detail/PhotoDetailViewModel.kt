package com.example.android.photos.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.photos.photos.Photo
import com.example.android.photos.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _currentPhoto = MutableLiveData<Photo>()
    val currentPhoto: LiveData<Photo> = _currentPhoto

    init {
        _currentPhoto.value = savedStateHandle.get("photo")
    }

    private val _currentUrl = MutableLiveData<Event<String?>>()
    val currentUrl: LiveData<Event<String?>> = _currentUrl

    fun onNavigateToSource() {
        _currentUrl.value = Event(currentPhoto.value?.url)
    }
}