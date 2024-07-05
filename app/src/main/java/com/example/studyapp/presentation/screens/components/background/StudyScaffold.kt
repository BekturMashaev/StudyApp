package com.example.studyapp.presentation.screens.components.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.studyapp.presentation.core.snackbar.ProvideSnackbarController
import com.example.studyapp.presentation.theme.Red
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.dp16

@Composable
fun StudyScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    content: @Composable (PaddingValues) -> Unit,
    dialog: @Composable () -> Unit = { },
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ProvideSnackbarController(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) {
                    Snackbar(
                        snackbarData = it,
                        containerColor = Red,
                        contentColor = White,
                        dismissActionContentColor = White
                    )
                }
            },
            topBar = topBar,
            bottomBar = bottomBar,
            containerColor = colorScheme.background,
            content = { paddingValues ->
                Box(
                    modifier
                        .fillMaxSize()
                        .padding(horizontal = dp16),
                    contentAlignment = Alignment.Center
                ) {
                    content(paddingValues)
                    dialog()
                }
            },
        )
    }
}

