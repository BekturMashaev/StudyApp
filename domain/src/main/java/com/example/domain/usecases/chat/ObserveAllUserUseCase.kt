package com.example.domain.usecases.chat

import com.example.domain.models.UserDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import kotlinx.coroutines.flow.Flow

typealias ListOfUsers = Flow<Result<List<UserDomainModel>, DataError.Network>>

interface ObserveAllUserUseCase {
    suspend fun observeAllUsers(): ListOfUsers
}