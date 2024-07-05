package com.example.studyapp.presentation.screens.auth.screens.register


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.screen.components.background.StudyScaffold
import com.example.studyapp.presentation.screen.components.buttons.SubmitButton
import com.example.studyapp.presentation.screen.components.text.field.EmailField
import com.example.studyapp.presentation.screen.components.text.field.PasswordField
import com.example.studyapp.presentation.screens.auth.screens.register.components.RegisterAgreementCheckBox
import com.example.studyapp.presentation.screens.auth.screens.register.components.RegisterTopAppBar
import com.example.studyapp.presentation.screens.auth.screens.register.components.SuccessDialog
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.dp16

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
    if (uiState == RegisterUIState.Success) {
        SuccessDialog(
            onClose = onNavigateToVerification
        )
    }
    if (uiState == RegisterUIState.Loading) {
        CircularProgressIndicator()
    }
    val (isEmailValid,emailErrorMessage) = validationState.isEmailNotValid
    val (isPasswordValid,passwordErrorMessage) = validationState.isPasswordNotValid
    StudyScaffold(
        topBar = {
            RegisterTopAppBar(
                onBackClick = onNavigateBack
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(innerPadding)
            ) {
                Spacer(modifier = modifier.size(dp16))
                EmailField(
                    onValueChange = { value ->
                        onInteractions(RegisterInteractions.OnEmailChanged(email = value))
                    },
                    value = userInfoState.email,
                    isValid = isEmailValid,
                    errorTextMessage = emailErrorMessage.asString(),
                )
                Spacer(modifier = modifier.size(dp16))
                PasswordField(
                    value = userInfoState.password,
                    onValueChange = { value ->
                        onInteractions(RegisterInteractions.OnPasswordChanged(password = value))
                    },
                    isValid = isPasswordValid,
                    errorTextMessage = passwordErrorMessage.asString(),
                )
                Spacer(modifier = modifier.size(dp16))
                RegisterAgreementCheckBox(
                    checked = validationState.isAgreedOnTerms,
                    onTermsClick = onTermsClick,
                    onCheckedChange = { checked ->
                        onInteractions(RegisterInteractions.OnCheckedChange(checked = checked))
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                SubmitButton(
                    text = stringResource(R.string.register),
                    onButtonClick = {
                        onInteractions(RegisterInteractions.OnRegisterButtonClick)
                    }
                )
                Spacer(modifier = Modifier.weight(0.1f))
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
            uiState = RegisterUIState.Loaded,
        )
    }
}