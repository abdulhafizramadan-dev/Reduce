package com.ahr.reduce.navigation

enum class Screen(val route: String) {

    LOGIN("login"),
    REGISTER("register"),
    PROFILE_SETTINGS("profileSettings"),
    DETAIL_ADDRESS("detailAddress"),
    DETAIL_PRODUCT("detailProduct/{productId}"),
    CHECKOUT("checkout"),


    AUTH_ROUTE("authRoute"),
    MAIN_ROUTE("mainRoute"),

    MAIN("main"),

    // Bottom Navigation
    HOME("home"),
    MARKET("market"),
    TRANSACTION("transaction"),
    PROFILE("profile"),
}