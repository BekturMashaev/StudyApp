package com.example.studyapp.di

import com.example.data.cloud.AuthCloudDataSource
import com.example.data.cloud.AuthCloudDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CloudDataSourceModule {
    @Binds
    @Singleton
    fun bindAuthCloudDataSource(
        impl: AuthCloudDataSourceImpl
    ): AuthCloudDataSource
}