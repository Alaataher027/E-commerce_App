package com.example.e_commerceapp.screens.main

import com.example.e_commerceapp.navigation_comp.bottomNav.BottomNavigationBar
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.navigation_comp.bottomNav.ECommerceBottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        ECommerceBottomNavigation(navController)
    }
}