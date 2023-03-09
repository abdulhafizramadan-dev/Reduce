package com.ahr.reduce.navigation

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
}

sealed class BottomBarScreen(val route: String) {
    object Home : BottomBarScreen("home")
    object Market : BottomBarScreen("market")
    object Transaction : BottomBarScreen("transaction")
    object Profile : BottomBarScreen("profile")
}

sealed class IndependentScreen(val route: String) {
    object ProfileSettings : IndependentScreen(
        route = "profile_settings?prev_screen={prev_screen}&next_screen={next_screen}"
    ) {
        fun getRoute(
            prevScreen: String? = null,
            nextScreen: String? = null
        ): String {
            return "profile_settings?prev_screen=$prevScreen&next_screen=$nextScreen"
        }

        const val PREV_SCREEN_KEY = "prev_screen"
        const val NEXT_SCREEN_KEY = "next_screen"
    }
    object DetailAddress : IndependentScreen(
        route = "detail_address?prev_screen={prev_screen}&next_screen={next_screen}"
    ) {
        fun getRoute(
            prevScreen: String? = null,
            nextScreen: String? = null
        ): String {
            return "detail_address/?prev_screen=$prevScreen&next_screen=$nextScreen"
        }

        const val PREV_SCREEN_KEY = "prev_screen"
        const val NEXT_SCREEN_KEY = "next_screen"
    }
    object DetailProduct : IndependentScreen("detail_product/{product_id}")
    object Checkout : IndependentScreen("checkout")
}