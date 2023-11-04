package com.example.e_commerceapp.navigation_comp.screensNav

enum class ECommerceScreens {

    MainScreen;

    companion object {
        fun fromRoute(route: String?): ECommerceScreens
                = when(route?.substringBefore("/")) {

            MainScreen.name -> MainScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}