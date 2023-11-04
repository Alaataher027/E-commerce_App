package com.example.e_commerceapp.screens.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    homeContect()
}

@Composable
fun homeContect() {
    Text(text = "home")

}