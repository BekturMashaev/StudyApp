package com.example.studyapp.presentation.screens.auth.screens.create.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.dp24

@Composable
fun CreateProfileTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.arrow_back),
                tint = colorScheme.onBackground,
                modifier = modifier.size(dp24),
            )
        }
        Text(
            text = stringResource(R.string.create_profile),
            color = colorScheme.onBackground,
            style = typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = modifier.weight(1f)
        )
    }
}