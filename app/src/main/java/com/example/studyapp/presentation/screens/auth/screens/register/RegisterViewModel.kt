package com.example.studyapp.presentation.screens.auth.screens.register

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.usecases.auth.register.UserRegisterUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: UserRegisterUseCase,
    private val validationFacade: ValidationFacade
) : ViewModel() {
    private val _userInfoState: MutableStateFlow<RegisterUserInfoState> =
        MutableStateFlow(RegisterUserInfoState())
    val userInfoState: StateFlow<RegisterUserInfoState> = _userInfoState.asStateFlow()

    private val _uiState: MutableStateFlow<RegisterUIState> =
        MutableStateFlow(RegisterUIState.Initial)
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    private val _userValidationState: MutableStateFlow<RegisterValidationState> =
        MutableStateFlow(RegisterValidationState())
    val userValidationState: StateFlow<RegisterValidationState> = _userValidationState.asStateFlow()

    fun onEvent(interaction: RegisterInteractions) {
        when (interaction) {
            is RegisterInteractions.OnEmailChanged -> doUserEmailChange(interaction)
            is RegisterInteractions.OnPasswordChanged -> doUserPasswordChange(interaction)
            RegisterInteractions.OnRegisterButtonClick -> doUserRegister()
            is RegisterInteractions.OnCheckedChange -> doCheckedChange(interaction)
        }
    }

    private fun doUserRegister() {
        viewModelScope.launch {
            _uiState.update {
                RegisterUIState.Loading
            }
            with(userInfoState.value) {
                if (userValidationState.value.isValidated()) {
                    val result = registerUseCase.registerNewUser(
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
                                RegisterUIState.Success
                            }
                        }
                    }
                } else {
                    showMessage(R.string.fill_in_all_the_blanks)
                }
            }
        }
    }

    private fun doUserEmailChange(interaction: RegisterInteractions.OnEmailChanged) {
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

    private fun doUserPasswordChange(interaction: RegisterInteractions.OnPasswordChanged) {
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

    private fun doCheckedChange(interaction: RegisterInteractions.OnCheckedChange) {
        _userValidationState.update {
            it.copy(
                isAgreedOnTerms = interaction.checked
            )
        }
    }

    private fun showMessage(message: Int) = _uiState.update {
        RegisterUIState.Error(
            snackbarMessage = SnackbarMessage.from(
                userMessage = UserMessage.from(resId = message),
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        )
    }
}