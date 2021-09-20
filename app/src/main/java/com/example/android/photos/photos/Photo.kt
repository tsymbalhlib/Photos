package com.example.android.photos.photos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val title: String,
    val thumbnailUrl: String,
    val url: String
) : Parcelable