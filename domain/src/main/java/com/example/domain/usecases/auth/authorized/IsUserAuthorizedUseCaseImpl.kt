package com.example.domain.usecases.auth.authorized

import com.example.domain.repository.AuthRepository

class IsUserAuthorizedUseCaseImpl(
    private val authRepository: AuthRepository
) : IsUserAuthorizedUseCase {
    override suspend fun invoke(): Boolean = authRepository.isUserAuthorized()
}