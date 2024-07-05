package com.example.domain.usecases.auth.create.profile

import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

interface CreateUserProfileUseCase {
    suspend operator fun invoke(user: UserSignDomainModel): Result<Unit, DataError.Network>
}