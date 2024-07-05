package com.example.domain.result.errortype

sealed interface DataError : Error {
    enum class Network : DataError {
        EMAIL_EXISTS,
        UNKNOWN
    }
}