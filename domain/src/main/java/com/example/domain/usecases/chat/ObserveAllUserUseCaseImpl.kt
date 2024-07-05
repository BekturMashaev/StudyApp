package com.example.domain.usecases.chat

import com.example.domain.repository.ChatRepository

class ObserveAllUserUseCaseImpl(
    private val repository: ChatRepository
) : ObserveAllUserUseCase {
    override suspend fun observeAllUsers(): ListOfUsers =
        repository.loadUsers()
}