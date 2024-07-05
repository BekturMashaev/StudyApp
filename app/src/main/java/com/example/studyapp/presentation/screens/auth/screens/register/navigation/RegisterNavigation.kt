package com.example.studyapp.presentation.screens.auth.screens.register.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterScreen
import com.example.studyapp.presentation.screens.auth.screens.register.RegisterViewModel

fun NavController.navigateToRegister() {
    navigate(RegisterDestination)
}

fun NavGraphBuilder.registerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToVerification: () -> Unit,
    onTermsClick: () -> Unit,
    navController: NavController
) {
    composable<RegisterDestination> { navBackStackEntry ->
        val registerViewModel =
            navBackStackEntry.sharedViewModel<RegisterViewModel>(navController = navController)
        val userInfoState by registerViewModel.userInfoState.collectAsState()
        val userValidationState by registerViewModel.userValidationState.collectAsState()
        val uiState by registerViewModel.uiState.collectAsState()
        RegisterScreen(
            onInteractions = registerViewModel::onEvent,
            userInfoState = userInfoState,
            validationState = userValidationState,
            onNavigateBack = onNavigateBack,
            onTermsClick = onTermsClick,
            uiState = uiState,
            onNavigateToVerification = onNavigateToVerification,
        )
    }
}