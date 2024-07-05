package com.example.domain.usecases.auth.login

import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError

interface UserLoginUseCase {
    suspend fun login(user: UserSignDomainModel): Result<Unit, DataError.Network>
}