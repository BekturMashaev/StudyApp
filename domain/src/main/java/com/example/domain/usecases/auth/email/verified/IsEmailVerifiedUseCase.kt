package com.example.domain.usecases.auth.email.verified

import kotlinx.coroutines.flow.Flow

interface IsEmailVerifiedUseCase {
    suspend operator fun invoke(): Flow<Boolean>
}