package com.example.studyapp.presentation.screens.auth.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.studyapp.R
import com.example.studyapp.presentation.screens.auth.screens.login.LoginInteractions
import com.example.studyapp.presentation.screens.auth.screens.login.LoginUserInfoState
import com.example.studyapp.presentation.screens.auth.screens.login.LoginValidationState
import com.example.studyapp.presentation.screens.components.buttons.SubmitButton
import com.example.studyapp.presentation.screens.components.field.EmailField
import com.example.studyapp.presentation.screens.components.field.PasswordField
import com.example.studyapp.presentation.theme.Poppins
import com.example.studyapp.presentation.theme.TextFieldHintColor
import com.example.studyapp.presentation.theme.dp16
import com.example.studyapp.presentation.theme.sp13
import com.example.studyapp.presentation.theme.sp14

@Composable
fun LoginContent(
    innerPadding: PaddingValues,
        onInteractions: (LoginInteractions) -> Unit,
    userInfoState: LoginUserInfoState,
    validationState: LoginValidationState,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToRegister: () -> Unit,
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
                onInteractions(LoginInteractions.OnEmailChanged(email = value))
            },
            value = userInfoState.email,
            isValid = isEmailValid,
            errorTextMessage = emailErrorMessage.asString(),
        )
        Spacer(modifier = modifier.size(dp16))
        PasswordField(
            value = userInfoState.password,
            onValueChange = { value ->
                onInteractions(LoginInteractions.OnPasswordChanged(password = value))
            },
            isValid = isPasswordValid,
            errorTextMessage = passwordErrorMessage.asString(),
        )
        ForgotPassword(onNavigateToChangePassword)
        Spacer(modifier = Modifier.weight(1f))
        SubmitButton(
            text = stringResource(R.string.login),
            onButtonClick = {
                onInteractions(LoginInteractions.OnLoginButtonClick)
            }
        )
        NoAccount(onNavigateToRegister)
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun ForgotPassword(
    onNavigateToChangePassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText =
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorScheme.primary, fontSize = sp14)) {
                append("Forgot password?")
            }
        }
    ClickableText(text = annotatedText) { offSet ->
        onNavigateToChangePassword()
    }
}

@Composable
fun NoAccount(
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = TextFieldHintColor,
                    fontSize = sp13,
                    fontFamily = Poppins
                )
            ) {
                append("Don’t have an account?  ")
                pushStringAnnotation(tag = "terms", annotation = "terms")
                withStyle(style = SpanStyle(color = colorScheme.primary, fontSize = sp14)) {
                    append("Sign Up")
                }
                pop()
            }
        }
    ClickableText(text = annotatedText) { offSet ->
        val startIndex = annotatedText.indexOf("terms and conditions")
        val endIndex = startIndex + "terms and conditions".length
        if (offSet in startIndex until endIndex) {
            onNavigateToRegister()
        }
    }
}