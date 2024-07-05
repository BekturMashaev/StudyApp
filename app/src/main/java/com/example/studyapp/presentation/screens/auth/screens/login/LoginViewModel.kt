package com.example.studyapp.presentation.screens.auth.screens.login

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.usecases.auth.login.UserLoginUseCase
import com.example.studyapp.R
import com.example.studyapp.presentation.core.ValidationFacade
import com.example.studyapp.presentation.core.snackbar.SnackbarMessage
import com.example.studyapp.presentation.core.snackbar.UserMessage
import com.example.studyapp.presentation.core.uitext.asNetworkErrorUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase,
    private val validationFacade: ValidationFacade
) : ViewModel() {
    private val _userInfoState: MutableStateFlow<LoginUserInfoState> =
        MutableStateFlow(LoginUserInfoState())
    val userInfoState: StateFlow<LoginUserInfoState> = _userInfoState.asStateFlow()

    private val _uiState: MutableStateFlow<LoginUIState> =
        MutableStateFlow(LoginUIState.Initial)
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    private val _userValidationState: MutableStateFlow<LoginValidationState> =
        MutableStateFlow(LoginValidationState())
    val userValidationState: StateFlow<LoginValidationState> = _userValidationState.asStateFlow()

    fun onEvent(interaction: LoginInteractions) {
        when (interaction) {
            is LoginInteractions.OnEmailChanged -> doUserEmailChange(interaction)
            LoginInteractions.OnLoginButtonClick -> doUserLogin()
            is LoginInteractions.OnPasswordChanged -> doUserPasswordChange(interaction)
        }
    }


    private fun doUserLogin() {
        viewModelScope.launch {
            _uiState.update {
                LoginUIState.Loading
            }
            with(userInfoState.value) {
                if (userValidationState.value.isValidated()) {
                    val result = loginUseCase.login(
                        UserSignDomainModel(
                            email = email,
                            password = password
                        )
                    )
                    when (result) {
                        is Result.Error -> {
                            showMessage(result.error.asNetworkErrorUiText())
                        }

                        is Result.Success -> {
                            _uiState.update {
                                LoginUIState.Success
                            }
                        }
                    }
                } else {
                    showMessage(R.string.fill_in_all_the_blanks)
                }
            }
        }
    }

    private fun doUserEmailChange(interaction: LoginInteractions.OnEmailChanged) {
        _userInfoState.update { state ->
            state.copy(
                email = interaction.email
            )
        }
        _userValidationState.update {
            it.copy(
                isEmailNotValid = validationFacade.isEmailValid(interaction.email)
            )
        }
    }

    private fun doUserPasswordChange(interaction: LoginInteractions.OnPasswordChanged) {
        _userInfoState.update { state ->
            state.copy(
                password = interaction.password
            )
        }
        _userValidationState.update {
            it.copy(
                isPasswordNotValid = validationFacade.isPasswordValid(interaction.password)
            )
        }
    }

    private fun showMessage(message: Int) = _uiState.update {
        LoginUIState.Error(
            snackbarMessage = SnackbarMessage.from(
                userMessage = UserMessage.from(resId = message),
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        )
    }
}