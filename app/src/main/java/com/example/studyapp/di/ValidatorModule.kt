package com.example.studyapp.di

import com.example.domain.validators.email.EmailValidation
import com.example.domain.validators.email.EmailValidationImpl
import com.example.domain.validators.password.PasswordValidation
import com.example.domain.validators.password.PasswordValidationImpl
import com.example.studyapp.presentation.core.ValidationFacade
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ValidatorModule {
    @Provides
    fun providePasswordValidator(): PasswordValidation {
        return PasswordValidationImpl()
    }

    @Provides
    fun provideEmailValidator(): EmailValidation {
        return EmailValidationImpl()
    }

    @Provides
    @Singleton
    fun provideFacade(
        emailValidator: EmailValidation,
        passwordValidator: PasswordValidation
    ): ValidationFacade = ValidationFacade(emailValidator, passwordValidator)
}