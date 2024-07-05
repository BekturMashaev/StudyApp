package com.example.studyapp.presentation.screens.auth.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.usecases.auth.email.verified.IsEmailVerifiedUseCase
import com.example.domain.usecases.auth.register.UserRegisterUseCase
import com.example.studyapp.presentation.core.ValidationFacade
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
    private val isEmailVerifiedUseCase: IsEmailVerifiedUseCase,
    private val validationFacade: ValidationFacade
) : ViewModel() {
    private val _userInfoState: MutableStateFlow<RegisterUserInfoState> =
        MutableStateFlow(RegisterUserInfoState())
    val userInfoState: StateFlow<RegisterUserInfoState> = _userInfoState.asStateFlow()

    val isFinished: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val _uiStateState: MutableStateFlow<RegisterUIState> =
        MutableStateFlow(RegisterUIState.Initial)
    val uiStateState: StateFlow<RegisterUIState> = _uiStateState.asStateFlow()

    private val _userValidationState: MutableStateFlow<RegisterValidationState> =
        MutableStateFlow(RegisterValidationState())
    val userValidationState: StateFlow<RegisterValidationState> = _userValidationState.asStateFlow()

    init {
        viewModelScope.launch {
            val emailVerification = isEmailVerifiedUseCase().collect { verified ->
                if (verified) _uiStateState.update {
                    RegisterUIState.Success
                }
            }
        }
    }

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
            _uiStateState.update {
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
                            _uiStateState.update {
                                RegisterUIState.Error
                            }
                        }

                        is Result.Success -> {
                            _uiStateState.update {
                                RegisterUIState.Loaded
                            }
                        }
                    }
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
}