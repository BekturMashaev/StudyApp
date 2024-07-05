package com.example.studyapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.auth.authorized.IsUserAuthorizedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyMainViewModel @Inject constructor(
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase,
) : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized = _isAuthorized.asStateFlow()

    init {
        viewModelScope.launch {
            _isAuthorized.value = isUserAuthorizedUseCase.invoke()
            delay(1000L)
            _isReady.value = true
        }
    }
}