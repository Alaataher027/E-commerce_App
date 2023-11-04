package com.example.e_commerceapp.screens.wishlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun WishListScreen(navController: NavHostController) {
    wishListContect()
}

@Composable
fun wishListContect() {
    Text(text = "WishListScreen")

}