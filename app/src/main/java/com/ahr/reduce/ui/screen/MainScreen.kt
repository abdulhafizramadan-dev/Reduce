package com.ahr.reduce.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.data.BottomNavigationItem
import com.ahr.reduce.navigation.MainNavigation
import com.ahr.reduce.navigation.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(navController = navController)
        },
        modifier = modifier
    ) { paddingValues ->
        MainNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun MainBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        BottomNavigationItem(
            title = R.string.menu_home,
            icon = Icons.Filled.Home,
            screen = BottomBarScreen.Home
        ),
        BottomNavigationItem(
            title = R.string.menu_market,
            icon = Icons.Filled.LocalMall,
            screen = BottomBarScreen.Market
        ),
        BottomNavigationItem(
            title = R.string.menu_transaction,
            icon = Icons.Filled.ReceiptLong,
            screen = BottomBarScreen.Transaction
        ),
        BottomNavigationItem(
            title = R.string.menu_profile,
            icon = Icons.Filled.Person,
            screen = BottomBarScreen.Profile
        ),
    )

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(modifier = modifier) {
        navigationItems.forEach { item ->

            val selected = item.screen.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = stringResource(id = item.title))
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}