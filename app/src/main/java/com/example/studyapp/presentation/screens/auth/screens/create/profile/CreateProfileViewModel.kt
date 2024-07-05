package com.example.studyapp.presentation.screens.auth.screens.create.profile

import android.net.Uri
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.usecases.auth.create.profile.CreateUserProfileUseCase
import com.example.studyapp.R
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
class CreateProfileViewModel @Inject constructor(
    private val createUserProfileUseCase: CreateUserProfileUseCase
) : ViewModel() {
    private val _userInfoState: MutableStateFlow<CreateProfileUserInfoState> =
        MutableStateFlow(CreateProfileUserInfoState())
    val userInfoState: StateFlow<CreateProfileUserInfoState> = _userInfoState.asStateFlow()

    var isReady = MutableStateFlow(false)
    private val _uiState: MutableStateFlow<CreateProfileUIState> =
        MutableStateFlow(CreateProfileUIState.Initial)
    val uiState: StateFlow<CreateProfileUIState> = _uiState.asStateFlow()


    fun onInteractions(interactions: CreateProfileInteractions) {
        when (interactions) {
            CreateProfileInteractions.OnContinueButtonClick -> onContinue()
            is CreateProfileInteractions.OnPhotoAdded -> onUserPhotoAdded(interactions.photo)
            is CreateProfileInteractions.OnUserNameChanged -> onUserNameChanged(interactions.name)
        }
    }

    private fun onContinue() {
        viewModelScope.launch {
            _uiState.update {
                CreateProfileUIState.Loading
            }
            if (_userInfoState.value.isValid()) {
                with(_userInfoState.value) {
                    val result = createUserProfileUseCase.invoke(
                        user = UserSignDomainModel(
                            username = name,
                            photoURI = photo.toString()
                        )
                    )
                    when (result) {
                        is Result.Error -> {
                            showMessage(result.error.asNetworkErrorUiText())
                        }

                        is Result.Success -> {
                            _uiState.update {
                                CreateProfileUIState.Success
                            }
                        }
                    }
                    isReady.update {
                        true
                    }
                }
            } else {
                showMessage(R.string.fill_in_all_the_blanks)
            }
        }
    }

    private fun onUserPhotoAdded(uri: Uri?) {
        _userInfoState.update {
            it.copy(
                photo = uri
            )
        }
    }

    private fun onUserNameChanged(name: String) {
        _userInfoState.update {
            it.copy(
                name = name
            )
        }
    }

    private fun showMessage(message: Int) = _uiState.update {
        CreateProfileUIState.Error(
            snackbarMessage = SnackbarMessage.from(
                userMessage = UserMessage.from(resId = message),
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        )
    }
}