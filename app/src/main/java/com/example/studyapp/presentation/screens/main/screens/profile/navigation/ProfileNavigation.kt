package com.example.studyapp.presentation.screens.main.screens.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.screens.main.screens.calendar.navigation.CalendarDestination

fun NavController.navigateToProfile() {
    navigate(ProfileDestination)
}
fun NavGraphBuilder.profileScreen(
    navController: NavController,
){
    composable<ProfileDestination> {  }
}