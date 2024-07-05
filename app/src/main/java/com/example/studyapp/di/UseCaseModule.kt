package com.example.studyapp.di

import com.example.domain.repository.AuthRepository
import com.example.domain.usecases.auth.authorized.IsUserAuthorizedUseCase
import com.example.domain.usecases.auth.authorized.IsUserAuthorizedUseCaseImpl
import com.example.domain.usecases.auth.create.profile.CreateUserProfileUseCase
import com.example.domain.usecases.auth.create.profile.CreateUserProfileUseCaseImpl
import com.example.domain.usecases.auth.email.verified.IsEmailVerifiedUseCase
import com.example.domain.usecases.auth.email.verified.IsEmailVerifiedUseCaseImpl
import com.example.domain.usecases.auth.register.UserRegisterUseCase
import com.example.domain.usecases.auth.register.UserRegisterUseCaseImpl
import com.example.domain.usecases.auth.sign.out.SignOutUseCase
import com.example.domain.usecases.auth.sign.out.SignOutUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideUserRegisterUseCase(
        repository: AuthRepository,
    ): UserRegisterUseCase = UserRegisterUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideIsUserAuthorizedUseCase(
        repository: AuthRepository,
    ): IsUserAuthorizedUseCase = IsUserAuthorizedUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        repository: AuthRepository,
    ): SignOutUseCase = SignOutUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideCreateUserProfileUseCase(
        repository: AuthRepository,
    ): CreateUserProfileUseCase = CreateUserProfileUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideIsEmailVerifiedUseCase(
        repository: AuthRepository,
    ): IsEmailVerifiedUseCase = IsEmailVerifiedUseCaseImpl(repository)
}