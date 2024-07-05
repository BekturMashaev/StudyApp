package com.example.domain.usecases.auth.email.verified

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class IsEmailVerifiedUseCaseImpl(
    private val repository: AuthRepository
) : IsEmailVerifiedUseCase {
    override suspend fun invoke(): Flow<Boolean> = repository.observeIsEmailVerified()
}