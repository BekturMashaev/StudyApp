package com.example.studyapp.presentation.screens.auth.screens.create.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.screens.auth.screens.create.profile.CreateProfileScreen

fun NavController.navigateToCreateProfile() {
    navigate(CreateProfileDestination)
}

fun NavGraphBuilder.createProfileScreen(
    navController: NavController
) {
    composable<CreateProfileDestination> {
        CreateProfileScreen()
    }
}