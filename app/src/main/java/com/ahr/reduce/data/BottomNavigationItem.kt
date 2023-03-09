package com.ahr.reduce.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahr.reduce.navigation.BottomBarScreen

data class BottomNavigationItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: BottomBarScreen
)
