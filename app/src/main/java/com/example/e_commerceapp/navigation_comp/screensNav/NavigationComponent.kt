package com.example.e_commerceapp.navigation_comp.screensNav

import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.login.LoginActivity
import com.example.e_commerceapp.register.RegisterActivity
import com.example.e_commerceapp.screens.main.MainScreen
import com.example.e_commerceapp.splash.SplashActivity


@Composable
fun ECommerceNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ECommerceScreens.MainScreen.name
    ) {

        composable(ECommerceScreens.MainScreen.name) {
            MainScreen()
        }

    }
}