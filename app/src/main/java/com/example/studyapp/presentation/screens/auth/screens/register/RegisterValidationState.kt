package com.example.studyapp.presentation.screens.auth.screens.register

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.core.uitext.UiText

@Immutable
data class RegisterValidationState(
    val isEmailNotValid: Pair<Boolean, UiText> = Pair(true, UiText.asNullString()),
    val isPasswordNotValid: Pair<Boolean, UiText> = Pair(true, UiText.asNullString()),
    val isAgreedOnTerms: Boolean = false,
) {
    fun isValidated(): Boolean {
        return isEmailNotValid.first && isPasswordNotValid.first && isAgreedOnTerms
    }
}