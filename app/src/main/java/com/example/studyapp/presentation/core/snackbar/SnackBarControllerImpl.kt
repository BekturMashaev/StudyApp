package com.example.studyapp.presentation.core.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Immutable
private class SnackBarControllerImpl(
    private val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope
) : SnackbarController {
    override fun showMessage(
        message: String,
        withDismissAction: Boolean,
        duration: SnackbarDuration,
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    override fun showMessage(
        snackbarVisuals: SnackbarVisuals,
        onSnackbarResult: (SnackbarResult) -> Unit
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(visuals = snackbarVisuals).let(onSnackbarResult)
        }
    }
}

@Stable
fun SnackbarController(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
): SnackbarController = SnackBarControllerImpl(
    snackbarHostState = snackbarHostState,
    coroutineScope = coroutineScope
)

@Composable
fun ProvideSnackbarController(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSnackbarController provides SnackbarController(
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        ),
        content = content
    )
}

val LocalSnackbarController = staticCompositionLocalOf<SnackbarController> {
    error("No SnackbarController provided.")
}
