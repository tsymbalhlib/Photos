package com.example.android.photos.util

sealed class PhotoApiStatus {
    object Loading : PhotoApiStatus()
    data class Error(val message: Int) : PhotoApiStatus()
    object Success : PhotoApiStatus()
}
