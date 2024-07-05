package com.example.studyapp.presentation.screens.main.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    signOut:()->Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = signOut) {
        Text(text = "SignOut")
    }
}