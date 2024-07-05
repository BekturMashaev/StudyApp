package com.example.studyapp.presentation.screens.auth.screens.create.profile

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.core.snackbar.SnackbarMessage

@Immutable
sealed interface CreateProfileUIState {
    data object Initial : CreateProfileUIState
    data object Loading : CreateProfileUIState
    data object Success : CreateProfileUIState
    data class Error(val snackbarMessage: SnackbarMessage?) : CreateProfileUIState
}