package com.example.studyapp.presentation.screens.auth.screens.register

import androidx.compose.runtime.Immutable

@Immutable
sealed interface RegisterUIState {
    data object Initial: RegisterUIState
    data object Loaded: RegisterUIState
    data object Loading: RegisterUIState
    data object Success: RegisterUIState
    data object Error: RegisterUIState
}