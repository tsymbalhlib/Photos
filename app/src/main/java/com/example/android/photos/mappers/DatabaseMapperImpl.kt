package com.example.android.photos.mappers

import com.example.android.photos.database.PhotoEntity
import com.example.android.photos.photos.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseMapperImpl @Inject constructor() : DatabaseMapper {
    override fun asPhoto(list: List<PhotoEntity>): List<Photo> {
        return list.map {
            Photo(
                title = it.title,
                thumbnailUrl = it.thumbnailUrl,
                url = it.url
            )
        }
    }
}