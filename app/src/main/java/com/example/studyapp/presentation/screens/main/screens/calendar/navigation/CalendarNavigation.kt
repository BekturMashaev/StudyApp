package com.example.studyapp.presentation.screens.main.screens.calendar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.studyapp.presentation.screens.auth.screens.register.navigation.RegisterDestination
import com.example.studyapp.presentation.screens.main.screens.home.navigation.HomeDestination

fun NavController.navigateToCalendar() {
    navigate(CalendarDestination)
}
fun NavGraphBuilder.calendarScreen(
    navController: NavController,
){
    composable<CalendarDestination> {  }

}