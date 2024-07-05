package com.example.data.repository

import com.example.data.cloud.AuthCloudDataSource
import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.AuthRepository
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authCloudDataSource: AuthCloudDataSource
) : AuthRepository {
    override suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network> =
        authCloudDataSource.registerNewUser(user)

    override suspend fun login(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserProfile(user: UserSignDomainModel): Result<Unit, DataError.Network> =
        authCloudDataSource.createUserProfile(user)

    override suspend fun observeIsUserAuthorized(): Boolean =
        authCloudDataSource.isUserAuthorized()

    override suspend fun signOut() = authCloudDataSource.signOut()

    override suspend fun observeIsEmailVerified(): Flow<Boolean> =
        authCloudDataSource.observeIsEmailVerified()
}