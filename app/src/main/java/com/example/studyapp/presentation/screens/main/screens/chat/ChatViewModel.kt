package com.example.studyapp.presentation.screens.main.screens.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.models.UserDomainModel
import com.example.domain.repository.ChatRepository
import com.example.domain.result.Result
import com.example.domain.usecases.chat.ObserveAllUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val observeAllUserUseCase: ObserveAllUserUseCase,
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _userInfoState: MutableStateFlow<List<UserDomainModel>> =
        MutableStateFlow(emptyList())
    val userInfoState: StateFlow<List<UserDomainModel>> = _userInfoState.asStateFlow()

    private val _messagesState: MutableStateFlow<List<String>> =
        MutableStateFlow(emptyList())
    val messagesState: StateFlow<List<String>> = _messagesState.asStateFlow()

    init {
        viewModelScope.launch {
            observeAllUserUseCase.observeAllUsers().collect { result ->
                when (result) {
                    is Result.Error -> Log.d("AAA", "message ${result.error}")
                    is Result.Success -> {
                        if (result.data.isNotEmpty()) {
                            _userInfoState.update {
                                result.data
                            }
                        }
                    }
                }
            }
        }
    }

    fun createMessage(
        uid: String,
        text: String
    ) {
        viewModelScope.launch {
            chatRepository.startMessagingInChats(uid, text = text).collect { result ->
                when (result) {
                    is Result.Error -> {
                        Log.d("AAA", "message error observe")
                        Log.d("AAA", "message error observe dd ${result.error}")
                    }
                    is Result.Success -> {
                        Log.d("AAA", "message good observe")
                    }
                }
            }
        }
    }

    fun observeMessages(
        uid: String,
    ) {
        viewModelScope.launch {
            chatRepository.observeMessagingInChats(uid).collect { result ->
                when (result) {
                    is Result.Error -> Log.d("AAA", "message error observe")
                    is Result.Success -> {
                        _messagesState.update {
                            result.data
                        }
                    }
                }
            }
        }
    }
}