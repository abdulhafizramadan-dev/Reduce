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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.data.BottomNavigationItem
import com.ahr.reduce.navigation.MainNavigation
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigator: Navigator,
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(navController = navController)
        },
        modifier = modifier
    ) { paddingValues ->
        MainNavigation(
            navController = navController,
            navigator = navigator,
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
            screen = Screen.HOME
        ),
        BottomNavigationItem(
            title = R.string.menu_market,
            icon = Icons.Filled.LocalMall,
            screen = Screen.MARKET
        ),
        BottomNavigationItem(
            title = R.string.menu_transaction,
            icon = Icons.Filled.ReceiptLong,
            screen = Screen.TRANSACTION
        ),
        BottomNavigationItem(
            title = R.string.menu_profile,
            icon = Icons.Filled.Person,
            screen = Screen.PROFILE
        ),
    )

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(modifier = modifier) {
        navigationItems.forEach { item ->

            val selected = item.screen.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.screen.route)
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