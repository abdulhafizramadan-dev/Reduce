package com.ahr.reduce.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.Graph
import com.ahr.reduce.navigation.IndependentScreen
import com.ahr.reduce.navigation.ReduceNavigation
import com.ahr.reduce.ui.theme.ReduceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReduceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val onBoardingState by mainViewModel.onBoardingState.collectAsState()
                    navController = rememberNavController()
                    val startDestination =
                        if (onBoardingState) Graph.Auth.route else IndependentScreen.OnBoarding.route
                    ReduceNavigation(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
