package com.example.android.photos.di

import android.content.Context
import androidx.room.Room
import com.example.android.photos.database.PhotoDatabase
import com.example.android.photos.database.PhotoDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): PhotoDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            PhotoDatabase::class.java,
            "photos_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(database: PhotoDatabase): PhotoDatabaseDao {
        return database.photoDatabaseDao
    }
}