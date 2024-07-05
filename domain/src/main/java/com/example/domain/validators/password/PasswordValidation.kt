package com.example.domain.validators.password

import com.example.domain.result.Result
import com.example.domain.result.errortype.PasswordError

interface PasswordValidation {

    fun validatePassword(password: String): Result<Unit, PasswordError>

}