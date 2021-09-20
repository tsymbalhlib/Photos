package com.example.android.photos.di

import com.example.android.photos.mappers.NetworkMapper
import com.example.android.photos.mappers.NetworkMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkMapperModule {

    @Binds
    abstract fun bindNetworkMapper(impl: NetworkMapperImpl): NetworkMapper
}