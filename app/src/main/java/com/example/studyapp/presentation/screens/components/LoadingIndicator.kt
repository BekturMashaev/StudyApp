package com.example.studyapp.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.dp8

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingIndicatorPreview() {
    StudyAppTheme {
        LoadingIndicator()
    }
}