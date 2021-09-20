package com.example.android.photos.mappers

import com.example.android.photos.database.PhotoEntity
import com.example.android.photos.photos.Photo

interface DatabaseMapper {
    fun asPhoto(list: List<PhotoEntity>): List<Photo>
}