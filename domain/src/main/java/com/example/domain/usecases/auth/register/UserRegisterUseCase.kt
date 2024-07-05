package com.example.domain.usecases.auth.register

import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

interface UserRegisterUseCase {
    suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network>
}