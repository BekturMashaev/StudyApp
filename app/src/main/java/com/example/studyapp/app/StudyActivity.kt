package com.example.studyapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.studyapp.presentation.navigation.AppNavGraph
import com.example.studyapp.presentation.theme.StudyAppTheme
import com.example.studyapp.presentation.utils.ChangeSystemBarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudyActivity : ComponentActivity() {
    private val viewmodel by viewModels<StudyMainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewmodel.isReady.value
            }
        }
        enableEdgeToEdge()
        setContent {
            StudyAppTheme {
                ChangeSystemBarsTheme(!isSystemInDarkTheme())
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isUserAuthorized by viewmodel.isAuthorized.collectAsState()
                    AppNavGraph(isUserAuthorized = isUserAuthorized)
                }
            }
        }
    }
}