package com.example.studyapp.presentation.screens.main.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.studyapp.R
import com.example.studyapp.presentation.screens.main.screens.calendar.navigation.CalendarDestination
import com.example.studyapp.presentation.screens.main.screens.chat.navigation.ChatDestination
import com.example.studyapp.presentation.screens.main.screens.home.navigation.HomeDestination
import com.example.studyapp.presentation.screens.main.screens.profile.navigation.ProfileDestination

@Composable
fun NavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navigationBarRoutes = listOf(
        BottomNavigationItem(
            route = HomeDestination,
            icon = R.drawable.home_icon
        ),
        BottomNavigationItem(
            route = CalendarDestination,
            icon = R.drawable.calendar_icon
        ),
        BottomNavigationItem(
            route = ChatDestination,
            icon = R.drawable.chat_icon
        ),
        BottomNavigationItem(
            route = ProfileDestination,
            icon = R.drawable.profile_icon
        )
    )
    NavigationBar {
        navigationBarRoutes.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = if (index == selectedItemIndex) colorScheme.inverseSurface
                        else colorScheme.inversePrimary
                    )
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val route: Any,
    val icon: Int
)