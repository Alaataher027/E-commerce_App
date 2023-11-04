package com.example.e_commerceapp.dataBase

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.e_commerceapp.model.AppUser


fun getCollectionRef(collectionName: String): CollectionReference {
    val db = Firebase.firestore
    return db.collection(collectionName)
}

fun addUserToFirestoreDB(
    appUser: AppUser,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(AppUser.COLLECTION_NAME)
        .document(appUser.id!!)
        .set(appUser)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getUserFromFirestoreDB(
    uid: String,
    onSuccessListener: OnSuccessListener<DocumentSnapshot>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(AppUser.COLLECTION_NAME)
        .document(uid)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

fun updateUserInFirestoreDB(
    updatedUser: AppUser,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    val db = Firebase.firestore
    val collectionRef: CollectionReference = db.collection(AppUser.COLLECTION_NAME)

    // Ensure that the user has a valid ID
    val userId = updatedUser.id
    if (userId != null) {
        // Use the document ID (userId) to update the user's data
        collectionRef.document(userId)
            .set(updatedUser)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    } else {
        // Handle the case where the user's ID is not available or valid
        onFailureListener.onFailure(Exception("Invalid user ID"))
    }
}