package com.example.domain.usecases.auth.sign.out

import com.example.domain.repository.AuthRepository

class SignOutUseCaseImpl(
    private val authRepository: AuthRepository
) : SignOutUseCase {
    override suspend fun signOut() = authRepository.signOut()
}