package com.example.studyapp.presentation.screen.components.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.studyapp.presentation.theme.dp16

@Composable
fun StudyScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        containerColor = colorScheme.background,
        content = { paddingValues ->
            Box(
                modifier
                    .fillMaxSize()
                    .padding(horizontal = dp16),
                contentAlignment = Alignment.TopCenter
            ) {
                content(paddingValues)
            }
        },
    )
}

