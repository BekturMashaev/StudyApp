package com.example.studyapp.presentation.screens.main.screens.chat.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.main.screens.chat.ChatScreen
import com.example.studyapp.presentation.screens.main.screens.chat.ChatViewModel
import com.example.studyapp.presentation.screens.main.screens.chat.navigateToChatMessage

fun NavController.navigateToChat() {
    navigate(ChatDestination)
}

fun NavGraphBuilder.chatScreen(
    navController: NavController,
) {
    composable<ChatDestination> { navBackStackEntry ->
        val viewModel =
            navBackStackEntry.sharedViewModel<ChatViewModel>(navController = navController)
        val users by viewModel.userInfoState.collectAsState()
        ChatScreen(
            users,
            onNavigate = { navController.navigateToChatMessage(it) }
        )
    }

}