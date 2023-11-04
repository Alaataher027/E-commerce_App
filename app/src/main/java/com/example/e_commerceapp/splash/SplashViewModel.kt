package com.example.e_commerceapp.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.dataBase.getUserFromFirestoreDB
import com.example.e_commerceapp.model.AppUser
import com.example.e_commerceapp.model.DataUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel : ViewModel() {
    var navigator: Navigator? = null
    val auth = Firebase.auth
    fun isCurrentUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun navigate() {
        if (isCurrentUserLoggedIn()) {
            // get User Data From Firestore
            getUserFromFirestoreDB(
                auth.currentUser?.uid!!,
                onSuccessListener = {
                    DataUtils.appUser = it.toObject(AppUser::class.java)
                    DataUtils.firebaseUser = auth.currentUser
                    // Navigate To Home
                    navigator?.navigateToHomeScreen()
                },
                onFailureListener = {
                    // navigate to Login
                    Log.e("Tag", it.localizedMessage)
                    navigator?.navigateToLoginScreen()
                })
        } else {
            // navigate to login Screen
            navigator?.navigateToLoginScreen()

        }

    }
}