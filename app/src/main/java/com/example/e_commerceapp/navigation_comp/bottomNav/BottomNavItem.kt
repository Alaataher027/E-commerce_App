package com.example.e_commerceapp.navigation_comp.bottomNav

import com.example.e_commerceapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.home,"home")
    object Categories: BottomNavItem("Categories",R.drawable.category,"categories")
    object WishList: BottomNavItem("WishList",R.drawable.heart,"wishlist")
    object Profile: BottomNavItem("Profile",R.drawable.user_icon,"profile")
}