package com.example.domain.repository

import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network>
    suspend fun createUserProfile(user: UserSignDomainModel): Result<Unit, DataError.Network>
    suspend fun isUserAuthorized(): Boolean
    suspend fun signOut()
    suspend fun observeIsEmailVerified(): Flow<Boolean>
}