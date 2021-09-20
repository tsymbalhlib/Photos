package com.example.android.photos.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract val photoDatabaseDao: PhotoDatabaseDao
}