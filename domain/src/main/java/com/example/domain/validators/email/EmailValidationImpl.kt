package com.example.domain.validators.email

import com.example.domain.result.Result
import com.example.domain.result.errortype.EmailError

private val EMAIL_ADDRESS_PATTERN = Regex(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
)

private val ALLOWED_DOMAINS = listOf(".com", ".ru")

class EmailValidationImpl : EmailValidation {
    override fun validateEmail(email: String): Result<Unit, EmailError> =
        if (email.isEmpty()) Result.Error(EmailError.IS_EMPTY)
        else if (!email.matches(EMAIL_ADDRESS_PATTERN) && !ALLOWED_DOMAINS.any { email.endsWith(it) }) Result.Error(
            EmailError.INVALID_FORMAT
        ) else Result.Success(Unit)
}