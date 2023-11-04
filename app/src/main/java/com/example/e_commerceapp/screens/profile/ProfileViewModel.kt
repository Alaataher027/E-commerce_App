package com.example.e_commerceapp.screens.profile

import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.dataBase.getUserFromFirestoreDB
import com.example.e_commerceapp.dataBase.updateUserInFirestoreDB
import com.example.e_commerceapp.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel : ViewModel() {
    val userData = MutableStateFlow<AppUser?>(null)

    init {

        getUserFromFirestoreDB(
            "uid",
            { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val appUser = documentSnapshot.toObject(AppUser::class.java)
                    userData.value = appUser
                }
            },
            {
                //  failure
            }
        )
    }

    fun updateUserData(newUserData: AppUser) {
        // Update user data in Firestore and update the local state
        updateUserInFirestoreDB(
            newUserData,
            {
                userData.value = newUserData
            },
            {
                //  failure
            }
        )
    }
}
