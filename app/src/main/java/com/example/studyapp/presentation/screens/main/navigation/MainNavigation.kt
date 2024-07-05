package com.example.studyapp.presentation.screens.main.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.presentation.screens.main.screens.calendar.navigation.calendarScreen
import com.example.studyapp.presentation.screens.main.screens.chat.chatMessageScreen
import com.example.studyapp.presentation.screens.main.screens.chat.navigation.chatScreen
import com.example.studyapp.presentation.screens.main.screens.home.navigation.HomeDestination
import com.example.studyapp.presentation.screens.main.screens.home.navigation.homeScreen
import com.example.studyapp.presentation.screens.main.screens.profile.navigation.profileScreen

fun NavController.navigateToMainGraph() {
    navigate(MainDestination)
}

fun NavGraphBuilder.mainGraph(
    onNavigateToLogin: () -> Unit
) {
    composable<MainDestination> {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeDestination,
                modifier = Modifier.padding(innerPadding)
            ) {
                homeScreen(
                    navController=navController,
                    onNavigateToLogin = onNavigateToLogin
                )
                calendarScreen(navController)
                chatScreen(navController)
                profileScreen(navController)
                chatMessageScreen(navController)
            }
        }
    }
}
