package com.example.studyapp.presentation.core

import com.example.domain.result.Result
import com.example.domain.validators.email.EmailValidation
import com.example.domain.validators.password.PasswordValidation
import com.example.studyapp.presentation.core.uitext.UiText
import com.example.studyapp.presentation.core.uitext.asEmailUiText
import com.example.studyapp.presentation.core.uitext.asPasswordUiText

class ValidationFacade(
    private val emailValidator: EmailValidation,
    private val passwordValidator: PasswordValidation
) {
    fun isEmailValid(email: String): Pair<Boolean, UiText> {
        return when (val result = emailValidator.validateEmail(email)) {
            is Result.Error -> {
                val errorMessage = result.error.asEmailUiText()
                Pair(false, errorMessage)
            }

            is Result.Success -> {
                Pair(true, UiText.asNullString())
            }
        }
    }

    fun isPasswordValid(password: String): Pair<Boolean, UiText> {
        return when (val result = passwordValidator.validatePassword(password)) {
            is Result.Error -> {
                val errorMessage = result.error.asPasswordUiText()
                Pair(false, errorMessage)
            }
            is Result.Success -> {
                Pair(true, UiText.asNullString())
            }
        }
    }
}