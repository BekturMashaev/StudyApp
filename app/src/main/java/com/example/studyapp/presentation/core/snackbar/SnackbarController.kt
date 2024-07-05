package com.example.studyapp.presentation.core.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Immutable

@Immutable
interface SnackbarController {
    fun showMessage(
        message: String,
        withDismissAction: Boolean = true,
        duration: SnackbarDuration = SnackbarDuration.Short,
    )

    fun showMessage(
        snackbarVisuals: SnackbarVisuals,
        onSnackbarResult: (SnackbarResult) -> Unit = {}
    )
}
