package com.training.movieapp.data.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) :
    UserRepository {
    override suspend fun updateProfile(username: String, bio: String, imageUri: Uri?) = flow {
        try {
            val imageURL = imageUri?.let { uploadImage(imageUri) }
            val currentUser = firebaseAuth.currentUser ?: throw NullPointerException("User is null")
            val userDocument =
                fireStore.collection("users").document(currentUser.uid)
            val userData = if (imageURL != null) {
                hashMapOf(
                    "username" to username,
                    "imageURL" to imageURL,
                    "bio" to bio
                )
            } else {
                hashMapOf(
                    "username" to username,
                    "bio" to bio
                )
            }
            userDocument.set(userData, SetOptions.merge()).await()
            val user =
                userDocument.get().await().toObject(User::class.java) ?: throw NullPointerException(
                    "User is null"
                )
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    private suspend fun uploadImage(imageUri: Uri) =
        coroutineScope {
            async {
                val imagesRef =
                    firebaseStorage.reference.child("images/${firebaseAuth.currentUser!!.uid}")
                imagesRef.putFile(imageUri).await()
                imagesRef.downloadUrl.await().toString()
            }
        }.await()

    override suspend fun checkUserIsLogged() = flow {
        try {
            firebaseAuth.currentUser ?: throw NullPointerException("User is null")
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}