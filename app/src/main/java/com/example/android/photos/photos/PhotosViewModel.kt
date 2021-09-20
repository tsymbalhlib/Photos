package com.example.android.photos.photos

import androidx.lifecycle.*
import com.example.android.photos.util.Event
import com.example.android.photos.repository.Repository
import com.example.android.photos.util.PhotoApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val photos = repository.getPhotosFromDb().asLiveData()

    private val _navigateToPhotoDetail = MutableLiveData<Event<Photo>>()
    val navigateToPhotoDetail: LiveData<Event<Photo>> = _navigateToPhotoDetail

    private val _photoApiStatus = MutableLiveData<PhotoApiStatus>()
    val photoApiStatus: LiveData<PhotoApiStatus> = _photoApiStatus

    private val _showSnackBarEventValue = MutableLiveData<Event<Int>>()
    val showSnackBarEventValue: LiveData<Event<Int>> = _showSnackBarEventValue

    init {
        getPhotos()
    }

    private fun getPhotos() = viewModelScope.launch {
        repository.getPhotosFromNetwork().collect { status ->
            _photoApiStatus.value = status
            when (status) {
                is PhotoApiStatus.Error -> {
                    _showSnackBarEventValue.value = Event(status.message)
                }
                else -> Unit
            }
        }
    }

    fun onNavigateToPhotoDetail(photo: Photo) {
        _navigateToPhotoDetail.value = Event(photo)
    }
}