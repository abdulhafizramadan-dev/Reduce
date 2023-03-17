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
    object OnBoarding : IndependentScreen("on_boarding")
    object ProfileSettings : IndependentScreen(route = "profile_settings?is_register_flow={is_register_flow}") {
        const val IS_REGISTER_FLOW_KEY = "is_register_flow"
        fun getRoute(
            isRegisterFlow: Boolean = false,
        ): String {
            return "profile_settings?is_register_flow=$isRegisterFlow"
        }
    }
    object DetailAddress : IndependentScreen(route = "detail_address?is_register_flow={is_register_flow}") {
        const val IS_REGISTER_FLOW_KEY = "is_register_flow"
        fun getRoute(
            isRegisterFlow: Boolean = false,
        ): String {
            return "detail_address?is_register_flow=$isRegisterFlow"
        }
    }
    object DetailProduct : IndependentScreen("detail_product/{document_id}") {
        const val DOCUMENT_ID_KEY = "document_id"
        fun getRoute(
            objectId: String?,
        ): String {
            return "detail_product/$objectId"
        }

    }

    object Cart : IndependentScreen("cart")
    object Checkout : IndependentScreen("checkout/{document_id}") {
        const val DOCUMENT_ID_KEY = "document_id"
        fun getRoute(
            objectId: String?,
        ): String {
            return "checkout/$objectId"
        }
    }
}