package com.example.android.photos.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(

    @PrimaryKey
    val url: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String
)
