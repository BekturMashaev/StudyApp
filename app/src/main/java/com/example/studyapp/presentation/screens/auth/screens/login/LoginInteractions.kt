package com.example.studyapp.presentation.screens.auth.screens.login

sealed interface LoginInteractions {
    data class OnEmailChanged(val email: String) : LoginInteractions
    data class OnPasswordChanged(val password: String) : LoginInteractions
    data object OnLoginButtonClick : LoginInteractions
}