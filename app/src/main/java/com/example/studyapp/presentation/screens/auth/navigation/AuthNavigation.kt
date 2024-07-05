package com.example.studyapp.presentation.screens.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.studyapp.presentation.screens.auth.screens.agreement.agreementScreen
import com.example.studyapp.presentation.screens.auth.screens.agreement.navigateToAgreement
import com.example.studyapp.presentation.screens.auth.screens.create.profile.navigation.CreateProfileDestination
import com.example.studyapp.presentation.screens.auth.screens.create.profile.navigation.createProfileScreen
import com.example.studyapp.presentation.screens.auth.screens.create.profile.navigation.navigateToCreateProfile
import com.example.studyapp.presentation.screens.auth.screens.register.navigation.RegisterDestination
import com.example.studyapp.presentation.screens.auth.screens.register.navigation.registerScreen

fun NavController.navigateToAuthGraph() {
    navigate(AuthDestination)
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onNavigateToHome: () -> Unit
) {
    navigation<AuthDestination>(
        startDestination = RegisterDestination
    ) {
        registerScreen(
            onNavigateToVerification = { navController.navigateToCreateProfile() },
            onNavigateBack = { navController.navigateUp() },
            onTermsClick = { navController.navigateToAgreement() },
            navController = navController
        )
        agreementScreen(
            onCloseClick = { navController.navigateUp() }
        )
        createProfileScreen(
            navController = navController
        )
    }
}