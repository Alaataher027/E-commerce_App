package com.example.e_commerceapp.navigation_comp.bottomNav

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_commerceapp.R
import com.example.e_commerceapp.navigation_comp.SelectedNavIcon

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Categories,
        BottomNavItem.WishList,
        BottomNavItem.Profile
    )

    BottomNavigation(
        modifier = Modifier.clip(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
            )
        ),
        backgroundColor = colorResource(id = R.color.mainColor),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val selected = currentRoute == item.screen_route
            if (selected) {
                BottomNavigationItem(
                    icon = { SelectedNavIcon(item.icon, item.title) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = colorResource(id = R.color.mainColor),
                    alwaysShowLabel = true,
                    selected = selected,
                    onClick = {
                        navController.navigate(item.screen_route)
                    }
                )
            } else {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            tint = Color.White,
                            contentDescription = item.title
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = colorResource(id = R.color.mainColor),
                    alwaysShowLabel = true,
                    selected = selected,
                    onClick = {
                        navController.navigate(item.screen_route)
                    }
                )
            }
        }
    }
}

