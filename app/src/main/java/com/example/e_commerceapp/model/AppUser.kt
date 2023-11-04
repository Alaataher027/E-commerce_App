package com.example.e_commerceapp.model

data class AppUser(
    val id: String? = null,
    val firstName: String? = null,
    val email: String? = null,
    val phoneNumber: String?= null,
) {
    companion object {
        const val COLLECTION_NAME = "Users"
    }
}

