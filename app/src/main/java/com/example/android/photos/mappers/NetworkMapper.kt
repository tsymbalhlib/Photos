package com.example.android.photos.mappers

import com.example.android.photos.database.PhotoEntity
import com.example.android.photos.network.PhotoNetwork

interface NetworkMapper {
    fun asPhotoEntity(list: List<PhotoNetwork>): List<PhotoEntity>
}