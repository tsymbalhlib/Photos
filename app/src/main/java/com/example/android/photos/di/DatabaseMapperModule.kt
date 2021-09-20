package com.example.android.photos.di

import com.example.android.photos.mappers.DatabaseMapper
import com.example.android.photos.mappers.DatabaseMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseMapperModule {

    @Binds
    abstract fun bindDatabaseMapper(impl: DatabaseMapperImpl): DatabaseMapper
}