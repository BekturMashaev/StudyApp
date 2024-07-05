package com.example.studyapp.presentation.screens.main.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.components.buttons.SubmitButton

@Composable
fun ChatMessageScreen(
    chatViewModel: ChatViewModel,
    uid: String,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        chatViewModel.observeMessages(uid)
    }
    var text by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.weight(1f)
        ) {
            items(chatViewModel.messagesState.value) { text ->
                Text(text = text)
            }
        }
        TextField(
            value = text,
            onValueChange = { textinput ->
                text = textinput
            }
        )
        SubmitButton(
            text = "Sumit",
            onButtonClick = { chatViewModel.createMessage(uid = uid, text = text) }
        )
    }
}


fun NavController.navigateToChatMessage(message: String) {
    navigate(ChatMessageDestination(message))
}

fun NavGraphBuilder.chatMessageScreen(
    navController: NavController,
) {
    composable<ChatMessageDestination> { navBackStackEntry ->
        val viewModel =
            navBackStackEntry.sharedViewModel<ChatViewModel>(navController = navController)
        val chatUuid: ChatMessageDestination = navBackStackEntry.toRoute()
        ChatMessageScreen(
            chatViewModel = viewModel,
            uid = chatUuid.uid
        )
    }

}