package com.example.e_commerceapp.screens.categories

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun CategoriesScreen(navController: NavHostController) {
    catigoriesContect()
}

@Composable
fun catigoriesContect() {
    Text(text = "CategoriesScreen")

}