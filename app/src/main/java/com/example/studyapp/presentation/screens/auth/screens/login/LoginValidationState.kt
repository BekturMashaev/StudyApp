package com.example.studyapp.presentation.screens.auth.screens.login

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.core.uitext.UiText

@Immutable
data class LoginValidationState(
    val isEmailNotValid: Pair<Boolean, UiText> = Pair(true, UiText.asNullString()),
    val isPasswordNotValid: Pair<Boolean, UiText> = Pair(true, UiText.asNullString()),
) {
    fun isValidated(): Boolean {
        return isEmailNotValid.first && isPasswordNotValid.first 
    }
}