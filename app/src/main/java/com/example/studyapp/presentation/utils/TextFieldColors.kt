package com.example.studyapp.presentation.utils

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import com.example.studyapp.presentation.theme.TextFieldHintColor
import com.example.studyapp.presentation.theme.WhiteBlue

@Composable
fun OutlinedTextFieldDefaults.customTextFieldColors() = with(colorScheme) {
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = onBackground,
        unfocusedTextColor = onBackground,
        errorTextColor = onError,
        unfocusedBorderColor = onSurface,
        focusedBorderColor = onSurface,
        unfocusedContainerColor = surface,
        focusedContainerColor = surface,
        errorContainerColor = errorContainer,
        cursorColor = WhiteBlue,
        unfocusedPlaceholderColor = TextFieldHintColor,
        focusedPlaceholderColor = TextFieldHintColor,
    )
}