package com.example.studyapp.app

import androidx.core.app.ComponentActivity
import com.example.studyapp.presentation.theme.StudyAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StudyActivity : ComponentActivity() {
    private val viewmodel by viewModels<StudyMainViewModel>()

    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("StateFlowValueCalledInComposition")
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