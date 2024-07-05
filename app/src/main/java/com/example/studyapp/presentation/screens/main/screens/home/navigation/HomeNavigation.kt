package com.example.studyapp.presentation.screens.main.screens.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.main.screens.calendar.navigation.CalendarDestination
import com.example.studyapp.presentation.screens.main.screens.home.HomeScreen
import com.example.studyapp.presentation.screens.main.screens.home.HomeViewModel

fun NavController.navigateToHome() {
    navigate(CalendarDestination)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToLogin:()->Unit,
    navController: NavController,
) {
    composable<HomeDestination> { navBackStackEntry ->
        val viewModel =
            navBackStackEntry.sharedViewModel<HomeViewModel>(navController = navController)
        HomeScreen(
            signOut = {
                onNavigateToLogin()
                viewModel.onItemClick() }
        )
    }
}