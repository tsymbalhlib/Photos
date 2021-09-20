package com.example.android.photos.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDatabaseDao {

    @Query("SELECT * FROM photos")
    fun getPhotos(): Flow<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)
}