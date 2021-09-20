package com.example.android.photos.network

data class PhotoNetwork(
    val albumId: Int,
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val url: String
)
