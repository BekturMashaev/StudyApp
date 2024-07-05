package com.example.domain.validators.password
import com.example.domain.result.Result
import com.example.domain.result.errortype.PasswordError

class PasswordValidationImpl : PasswordValidation {
    override fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.isEmpty()) {
            return Result.Error(PasswordError.IS_EMPTY)
        }
        if (password.length < 9) {
            return Result.Error(PasswordError.TOO_SHORT)
        }
        val hasAnyChar = password.any { it.isLetter() }
        if (!hasAnyChar) {
            return Result.Error(PasswordError.NO_CHAR)
        }
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (!hasUppercaseChar) {
            return Result.Error(PasswordError.NO_UPPERCASE)
        }
        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Error(PasswordError.NO_DIGIT)
        }
        return Result.Success(Unit)
    }
}