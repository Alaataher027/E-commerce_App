package com.example.e_commerceapp.navigation_comp.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerceapp.screens.categories.CategoriesScreen
import com.example.e_commerceapp.screens.home.HomeScreen
import com.example.e_commerceapp.screens.profile.ProfileScreen
import com.example.e_commerceapp.screens.wishlist.WishListScreen


@Composable
fun ECommerceBottomNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screen_route
    ) {

        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController = navController)
        }

        composable(BottomNavItem.Categories.screen_route) {
            CategoriesScreen(navController = navController)
        }

        composable(BottomNavItem.WishList.screen_route) {
            WishListScreen(navController = navController)
        }

        composable(BottomNavItem.Profile.screen_route) {

           ProfileScreen(navController = navController)
        }

    }
}