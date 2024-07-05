package com.example.studyapp.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        auth: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindChatRepository(
        auth: ChatRepositoryImpl
    ): ChatRepository
}