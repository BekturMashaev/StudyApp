package com.example.studyapp.presentation.screens.auth.screens.login.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.auth.screens.login.LoginScreen
import com.example.studyapp.presentation.screens.auth.screens.login.LoginViewModel

fun NavController.navigateToLogin() {
    navigate(LoginDestination)
}

fun NavGraphBuilder.loginScreen(
    onNavigateToChangePassword: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit,
    navController: NavController
) {
    composable<LoginDestination> { navBackStackEntry ->
        val viewModel =
            navBackStackEntry.sharedViewModel<LoginViewModel>(navController = navController)
        val userInfoState by viewModel.userInfoState.collectAsState()
        val userValidationState by viewModel.userValidationState.collectAsState()
        val uiState by viewModel.uiState.collectAsState()
        LoginScreen(
            onInteractions = viewModel::onEvent,
            userInfoState = userInfoState,
            validationState = userValidationState,
            uiState = uiState,
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToChangePassword = onNavigateToChangePassword,
            onNavigateToMain = { onNavigateToMain() }
        )
    }
}