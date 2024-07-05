package com.example.studyapp.presentation.screens.auth.screens.register.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.studyapp.R
import com.example.studyapp.presentation.screens.components.buttons.SubmitButton
import com.example.studyapp.presentation.screens.components.field.EmailField
import com.example.studyapp.presentation.screens.components.field.PasswordField
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterInteractions
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterUserInfoState
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterValidationState
import com.example.studyapp.presentation.theme.dp16

@Composable
fun RegisterContent(
    innerPadding: PaddingValues,
    onInteractions: (RegisterInteractions) -> Unit,
    userInfoState: RegisterUserInfoState,
    validationState: RegisterValidationState,
    onTermsClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val (isEmailValid, emailErrorMessage) = validationState.isEmailNotValid
    val (isPasswordValid, passwordErrorMessage) = validationState.isPasswordNotValid
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