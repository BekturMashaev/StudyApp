package com.example.domain.repository

import com.example.domain.models.UserDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import kotlinx.coroutines.flow.Flow

typealias ListOfUsers = Flow<Result<List<UserDomainModel>, DataError.Network>>

interface ChatRepository {
    suspend fun loadUsers(): ListOfUsers
    suspend fun observeMessagingInChats(opponentUUID: String): Flow<Result<List<String>, DataError.Network>>
    suspend fun startMessagingInChats(
        opponentUUID: String,
        text: String
    ): Flow<Result<Unit, DataError.Network>>
}