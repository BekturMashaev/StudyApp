package com.example.studyapp.presentation.screens.auth.screens.agreement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.R
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.theme.White
import com.example.studyapp.presentation.theme.dp46
import com.example.studyapp.presentation.theme.dp66
import com.example.studyapp.presentation.theme.dp8

@Composable
fun AgreementScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AgreementTopBar(
                onCloseClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize(0.8f)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(dp8))
            Text(
                text = stringResource(R.string.do_you_agree)
            )
            Spacer(modifier = Modifier.height(dp8))
            Button(
                onClick = onBackClick,
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = dp46, max = dp66)
            ) {
                Text(
                    text = "Agree",
                    color = White,
                    style = typography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AgreementTopBar(onCloseClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Terms and Conditions")
        },
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorScheme.background)
    )
}

@Preview
@Composable
private fun AgreementScreenPreview() {
    StudyAppTheme {
        AgreementScreen(onBackClick = {})
    }
}