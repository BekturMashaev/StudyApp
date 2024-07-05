package com.example.studyapp.presentation.screens.auth.screens.register

sealed interface RegisterInteractions {
    data class OnEmailChanged(val email: String) : RegisterInteractions
    data class OnPasswordChanged(val password: String) : RegisterInteractions
    data object OnRegisterButtonClick : RegisterInteractions
    data class OnCheckedChange (val checked:Boolean ) : RegisterInteractions
}