package com.example.studyapp.presentation.screens.auth.screens.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.studyapp.R
import com.example.studyapp.presentation.core.snackbar.SnackbarMessageHandler
import com.example.studyapp.presentation.screens.auth.screens.login.components.LoginContent
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterUIState
import com.example.studyapp.presentation.screens.auth.screens.register.components.RegisterContent
import com.example.studyapp.presentation.screens.auth.screens.register.components.SuccessDialog
import com.example.studyapp.presentation.screens.components.LoadingIndicator
import com.example.studyapp.presentation.screens.components.background.StudyScaffold

@Composable
fun LoginScreen(
    onInteractions: (LoginInteractions) -> Unit,
    userInfoState: LoginUserInfoState,
    validationState: LoginValidationState,
    uiState: LoginUIState,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit,
    modifier: Modifier = Modifier
) {

    StudyScaffold(
        topBar = {
            Text(
                text = stringResource(R.string.login),
                color = colorScheme.onBackground,
                style = typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        },
        content = { innerPadding ->
            LoginContent(
                innerPadding = innerPadding,
                onInteractions = onInteractions,
                userInfoState = userInfoState,
                validationState = validationState,
                onNavigateToChangePassword = onNavigateToChangePassword,
                onNavigateToRegister = onNavigateToRegister)
            when (uiState) {
                is LoginUIState.Error -> {
                    SnackbarMessageHandler(
                        snackbarMessage = uiState.snackbarMessage,
                    )
                }
                is LoginUIState.Loading -> {
                    LoadingIndicator()
                }
                is LoginUIState.Success->{
                    onNavigateToMain()
                }
                else -> {}
            }
        }
    )
}