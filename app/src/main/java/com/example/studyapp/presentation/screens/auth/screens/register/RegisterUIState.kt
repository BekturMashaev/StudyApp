package com.example.studyapp.presentation.screens.auth.screens.register

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.core.snackbar.SnackbarMessage

@Immutable
sealed interface RegisterUIState {
    data object Initial : RegisterUIState
    data object Loading : RegisterUIState
    data object Success : RegisterUIState
    data class Error(val snackbarMessage: SnackbarMessage?) : RegisterUIState
}

