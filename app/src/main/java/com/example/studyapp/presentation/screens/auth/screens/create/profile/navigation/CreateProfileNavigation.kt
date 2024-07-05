package com.example.studyapp.presentation.screens.auth.screens.create.profile.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.navigation.sharedViewModel
import com.example.studyapp.presentation.screens.auth.screens.create.profile.CreateProfileScreen
import com.example.studyapp.presentation.screens.auth.screens.create.profile.CreateProfileViewModel

fun NavController.navigateToCreateProfile() {
    navigate(CreateProfileDestination)
}

fun NavGraphBuilder.createProfileScreen(
    onBackClick: () -> Unit,
    onNavigateToHome:()->Unit,
    navController: NavController
) {
    composable<CreateProfileDestination> { navBackStackEntry ->
        val viewModel =
            navBackStackEntry.sharedViewModel<CreateProfileViewModel>(navController = navController)
        val userInfoState by viewModel.userInfoState.collectAsState()
        val isReady by viewModel.isReady.collectAsState()
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(
            isReady
        ) {
            if (isReady) {
                onNavigateToHome()
            }
        }
        CreateProfileScreen(
            onInteractions = viewModel::onInteractions,
            textFieldValue = userInfoState.name,
            isValid = true,
            onBackClick = onBackClick,
            uiState = uiState
        )
    }
}