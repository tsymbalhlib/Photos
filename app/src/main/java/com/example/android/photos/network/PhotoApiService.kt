package com.example.android.photos.network

import retrofit2.Response
import retrofit2.http.GET

interface PhotoApiService {
    @GET("photos")
    suspend fun loadPhotos(): Response<List<PhotoNetwork>>
}