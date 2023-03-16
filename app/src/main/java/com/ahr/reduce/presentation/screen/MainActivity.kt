package com.ahr.reduce.presentation.screen

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.Graph
import com.ahr.reduce.navigation.IndependentScreen
import com.ahr.reduce.navigation.ReduceNavigation
import com.ahr.reduce.ui.theme.ReduceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        setContent {
            ReduceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val onBoardingState by mainViewModel.onBoardingState.collectAsState()
                    splashScreen.setKeepOnScreenCondition {
                        onBoardingState is UiState.Loading
                    }
                    if (onBoardingState is UiState.Success) {
                        navController = rememberNavController()
                        val startDestination =
                            if ((onBoardingState as UiState.Success).data) Graph.Auth.route else IndependentScreen.OnBoarding.route
                        ReduceNavigation(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                    if (onBoardingState is UiState.Error) {
                        navController = rememberNavController()
                        val startDestination = IndependentScreen.OnBoarding.route
                        ReduceNavigation(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}
