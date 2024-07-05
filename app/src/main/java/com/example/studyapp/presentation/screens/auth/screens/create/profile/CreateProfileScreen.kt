package com.example.studyapp.presentation.screens.auth.screens.create.profile

import android.net.Uri
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.presentation.core.snackbar.SnackbarMessageHandler
import com.example.studyapp.presentation.screens.components.background.StudyScaffold
import com.example.studyapp.presentation.screens.auth.screens.create.profile.components.CreateProfileContent
import com.example.studyapp.presentation.screens.auth.screens.create.profile.components.CreateProfileTopAppBar
import com.example.studyapp.presentation.screens.components.LoadingIndicator
import com.example.studyapp.presentation.theme.StudyAppTheme

@Composable
fun CreateProfileScreen(
    textFieldValue: String,
    isValid: Boolean,
    uiState: CreateProfileUIState,
    onBackClick: () -> Unit,
    onInteractions: (CreateProfileInteractions) -> Unit,
    modifier: Modifier = Modifier
) {
    StudyScaffold(
        topBar = {
            CreateProfileTopAppBar(
                onBackClick = onBackClick
            )
        },
        content = { innerPadding ->
            CreateProfileContent(
                innerPadding,
                onInteractions,
                isValid,
                textFieldValue,
            )
            when (uiState) {
                is CreateProfileUIState.Error -> {
                    SnackbarMessageHandler(
                        snackbarMessage = uiState.snackbarMessage,
                    )
                }
                is CreateProfileUIState.Loading -> {
                    LoadingIndicator()
                }
                else -> {}
            }

        }
    )
}

@Preview()
@Composable
private fun CreateProfileScreenPreview() {
    StudyAppTheme {
        CreateProfileScreen(
            onInteractions = {},
            textFieldValue = "aa",
            isValid = true,
            onBackClick = {},
            uiState = CreateProfileUIState.Initial
        )
    }
}