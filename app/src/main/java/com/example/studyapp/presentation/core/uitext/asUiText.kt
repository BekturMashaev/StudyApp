package com.example.studyapp.presentation.core.uitext

import com.example.domain.result.errortype.EmailError
import com.example.domain.result.errortype.PasswordError
import com.example.studyapp.R

fun PasswordError.asPasswordUiText(): UiText {
    return when (this) {
        PasswordError.IS_EMPTY -> UiText.StringResource(
            R.string.is_empty
        )

        PasswordError.TOO_SHORT -> UiText.StringResource(
            R.string.password_too_short
        )

        PasswordError.NO_CHAR -> UiText.StringResource(
            R.string.must_contain_one_char
        )

        PasswordError.NO_DIGIT -> UiText.StringResource(
            R.string.must_contain_one_digit
        )

        PasswordError.NO_UPPERCASE -> UiText.StringResource(
            R.string.must_contain_one_uppercase
        )
    }
}

fun EmailError.asEmailUiText(): UiText {
    return when (this) {
        EmailError.IS_EMPTY -> UiText.StringResource(
            R.string.is_empty
        )

        EmailError.INVALID_FORMAT -> UiText.StringResource(
            R.string.is_invalid_format
        )
    }
}