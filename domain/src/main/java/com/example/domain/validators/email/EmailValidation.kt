package com.example.domain.validators.email

import com.example.domain.result.Result
import com.example.domain.result.errortype.EmailError

interface EmailValidation {
    fun validateEmail(email: String): Result<Unit, EmailError>
}