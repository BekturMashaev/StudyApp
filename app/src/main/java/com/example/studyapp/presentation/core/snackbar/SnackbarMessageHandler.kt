package com.example.studyapp.presentation.core.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SnackbarMessageHandler(
    snackbarMessage: SnackbarMessage?,
    snackbarController: SnackbarController = LocalSnackbarController.current
) {
    if (snackbarMessage == null) return
    when (snackbarMessage) {
        is SnackbarMessage.Text -> {
            val message = snackbarMessage.userMessage.asString()
            LaunchedEffect(snackbarMessage) {
                snackbarController.showMessage(
                    message = message,
                    withDismissAction = snackbarMessage.withDismissAction,
                    duration = snackbarMessage.duration,
                )
            }
        }
        is SnackbarMessage.Visuals -> {
            LaunchedEffect(snackbarMessage) {
                snackbarController.showMessage(
                    snackbarVisuals = snackbarMessage.snackbarVisuals,
                    onSnackbarResult = snackbarMessage.onSnackbarResult
                )
            }
        }
    }
}