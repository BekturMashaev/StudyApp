package com.example.domain.result.errortype

enum class PasswordError : Error {
    TOO_SHORT,
    NO_UPPERCASE,
    NO_DIGIT,
    IS_EMPTY,
    NO_CHAR,
}