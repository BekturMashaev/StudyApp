package com.example.domain.usecases.auth.create.profile

import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.AuthRepository
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

class CreateUserProfileUseCaseImpl(
    val repository: AuthRepository
) : CreateUserProfileUseCase {
    override suspend fun invoke(user: UserSignDomainModel): Result<Unit, DataError.Network> =
        repository.createUserProfile(user)
}