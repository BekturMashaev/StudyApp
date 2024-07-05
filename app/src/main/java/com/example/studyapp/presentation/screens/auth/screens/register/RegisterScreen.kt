package com.example.studyapp.presentation.screens.auth.screens.register


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.presentation.core.snackbar.SnackbarMessageHandler
import com.example.studyapp.presentation.screens.auth.screens.register.components.RegisterContent
import com.example.studyapp.presentation.screens.auth.screens.register.components.RegisterTopAppBar
import com.example.studyapp.presentation.screens.auth.screens.register.components.SuccessDialog
import com.example.studyapp.presentation.screens.components.LoadingIndicator
import com.example.studyapp.presentation.screens.components.background.StudyScaffold
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.dp8

@Composable
fun RegisterScreen(
    onInteractions: (RegisterInteractions) -> Unit,
    userInfoState: RegisterUserInfoState,
    validationState: RegisterValidationState,
    uiState: RegisterUIState,
    onNavigateBack: () -> Unit,
    onTermsClick: () -> Unit,
    onNavigateToVerification: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StudyScaffold(
        topBar = {
            RegisterTopAppBar(
                onBackClick = onNavigateBack
            )
        },
        content = { innerPadding ->
            RegisterContent(
                innerPadding = innerPadding,
                onInteractions = onInteractions,
                userInfoState = userInfoState,
                validationState = validationState,
                onTermsClick = onTermsClick,
            )
            when (uiState) {
                is RegisterUIState.Error -> {
                    SnackbarMessageHandler(
                        snackbarMessage = uiState.snackbarMessage,
                    )
                }

                is RegisterUIState.Loading -> {
                    LoadingIndicator()
                }
                else -> {}
            }
        },
        dialog = {
            if (uiState == RegisterUIState.Success) {
                SuccessDialog(
                    onClose = onNavigateToVerification,
                    text = "Email's been sent"
                )
            }
        }
    )
}


@Preview
@Composable
private fun RegisterScreenPreview() {
    StudyAppTheme {
        RegisterScreen(
            onInteractions = {},
            userInfoState = RegisterUserInfoState(),
            validationState = RegisterValidationState(),
            onNavigateBack = {},
            onTermsClick = {},
            onNavigateToVerification = {},
            uiState = RegisterUIState.Initial,
        )
    }
}