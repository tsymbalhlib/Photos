package com.example.android.photos.di

import com.example.android.photos.repository.Repository
import com.example.android.photos.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}