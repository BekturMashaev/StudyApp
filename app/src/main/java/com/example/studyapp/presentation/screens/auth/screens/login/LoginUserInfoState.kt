package com.example.studyapp.presentation.screens.auth.screens.login

import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.utils.emptyString

@Immutable
data class LoginUserInfoState(
    val email: String = emptyString(),
    val password: String = emptyString()
) {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}