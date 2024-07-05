package com.example.domain.usecases.auth.login

import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.AuthRepository
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

class UserLoginUseCaseImpl(
    private val authRepository: AuthRepository
) : UserLoginUseCase {
    override suspend fun login(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        return authRepository.login(user)
    }
}