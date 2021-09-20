package com.example.android.photos.di

import com.example.android.photos.network.networkhelper.NetworkHelper
import com.example.android.photos.network.networkhelper.NetworkHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkHelperModule {

    @Binds
    abstract fun bindNetworkHelper(impl: NetworkHelperImpl): NetworkHelper
}