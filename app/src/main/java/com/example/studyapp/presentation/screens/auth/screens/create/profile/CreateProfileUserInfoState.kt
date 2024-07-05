package com.example.studyapp.presentation.screens.auth.screens.create.profile

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.example.studyapp.presentation.utils.emptyString

@Immutable
data class CreateProfileUserInfoState(
    val name: String = emptyString(),
    val photo: Uri? = null
) {
    fun isValid(): Boolean {
        return name.isNotEmpty()
    }
}