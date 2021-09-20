package com.example.android.photos.mappers

import com.example.android.photos.database.PhotoEntity
import com.example.android.photos.network.PhotoNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMapperImpl @Inject constructor() : NetworkMapper {
    override fun asPhotoEntity(list: List<PhotoNetwork>): List<PhotoEntity> {
        return list.map {
            PhotoEntity(
                url = it.url,
                title = it.title,
                thumbnailUrl = it.thumbnailUrl
            )
        }
    }
}