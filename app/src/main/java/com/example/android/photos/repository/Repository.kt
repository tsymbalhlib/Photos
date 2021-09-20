package com.example.android.photos.repository

import com.example.android.photos.photos.Photo
import com.example.android.photos.util.PhotoApiStatus
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPhotosFromDb(): Flow<List<Photo>>

    suspend fun getPhotosFromNetwork(): Flow<PhotoApiStatus>
}