package com.example.domain.usecases.auth.register

import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.AuthRepository
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

class UserRegisterUseCaseImpl(
    private val authRepository: AuthRepository
): UserRegisterUseCase {
    override suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        return authRepository.registerNewUser(user)
    }
}