package com.example.studyapp.presentation.screens.auth.screens.create.profile

import android.net.Uri

sealed interface CreateProfileInteractions {
    data class OnUserNameChanged(val name: String) : CreateProfileInteractions
    data class OnPhotoAdded(val photo: Uri?) : CreateProfileInteractions
    data object OnContinueButtonClick : CreateProfileInteractions
}