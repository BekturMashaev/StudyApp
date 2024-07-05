package com.example.studyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.presentation.screens.auth.navigation.AuthDestination
import com.example.studyapp.presentation.screens.auth.navigation.authGraph
import com.example.studyapp.presentation.screens.auth.navigation.navigateToAuthGraph
import com.example.studyapp.presentation.screens.main.MainDestination
import com.example.studyapp.presentation.screens.main.home.HomeDestination
import com.example.studyapp.presentation.screens.main.home.HomeScreen
import com.example.studyapp.presentation.screens.main.home.HomeViewModel

@Composable
fun AppNavGraph(
    isUserAuthorized: Boolean
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isUserAuthorized) MainDestination else AuthDestination
    ) {
        authGraph(
            navController = navController,
            onNavigateToHome = {
                navController.navigate(MainDestination) {
                    popUpTo(0)
                }
            }
        )
        navigation<MainDestination>(
            startDestination = HomeDestination
        ) {
            composable<HomeDestination> { navBackStackEntry ->
                val registerViewModel =
                    navBackStackEntry.sharedViewModel<HomeViewModel>(navController = navController)
                HomeScreen(
                    signOut = {
                        registerViewModel.onItemClick()
                        navController.navigateToAuthGraph()
                    }
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}