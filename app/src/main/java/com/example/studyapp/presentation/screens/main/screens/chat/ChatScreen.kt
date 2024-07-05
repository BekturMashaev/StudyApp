package com.example.studyapp.presentation.screens.main.screens.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.models.UserDomainModel

@Composable
fun ChatScreen(
    users: List<UserDomainModel>,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(users) { user ->
            TextButton(onClick = { onNavigate(user.userUUID) }) {
                Text(text = user.username)
            }
        }
    }
}

