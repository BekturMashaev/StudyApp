package com.example.domain.usecases.auth.authorized

interface IsUserAuthorizedUseCase {
    suspend operator fun invoke(): Boolean
}