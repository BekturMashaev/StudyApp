package com.example.studyapp.presentation.screens.auth.screens.login

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.core.snackbar.SnackbarMessage

@Immutable
sealed interface LoginUIState {
    data object Initial : LoginUIState
    data object Loading : LoginUIState
    data object Success : LoginUIState
    data class Error(val snackbarMessage: SnackbarMessage?) : LoginUIState
}