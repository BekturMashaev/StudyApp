package com.example.data.cloud

import android.net.Uri
import android.util.Log
import com.example.domain.models.UserDomainModel
import com.example.domain.models.UserSignDomainModel
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthCloudDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
) : AuthCloudDataSource {
    override suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network> =
        try {
            val result = suspendCancellableCoroutine<AuthResult> { continuation ->
                auth.createUserWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener { authResult ->
                        continuation.resume(authResult)
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
            if (result.user != null) {
                result.user!!.sendEmailVerification()
                Result.Success(Unit)
            } else {
                Result.Error(DataError.Network.UNKNOWN)
            }
        } catch (error: FirebaseAuthException) {
            when (error.errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> Result.Error(DataError.Network.EMAIL_EXISTS)
                else -> {
                    Log.d("AAA", "message fire ${error.errorCode}")
                    Result.Error(DataError.Network.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }

    override suspend fun login(user: UserSignDomainModel): Result<Unit, DataError.Network> = try {
        val result = suspendCancellableCoroutine<AuthResult> { continuation ->
            auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnSuccessListener { authResult ->
                    continuation.resume(authResult)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
        if (result.user != null) {
            Result.Success(Unit)
        } else {
            Result.Error(DataError.Network.UNKNOWN)
        }
    } catch (error: FirebaseAuthException) {
        when (error.errorCode) {
            "ERROR_EMAIL_ALREADY_IN_USE" -> Result.Error(DataError.Network.EMAIL_EXISTS)
            else -> {
                Log.d("AAA", "message fire ${error.errorCode}")
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Result.Error(DataError.Network.UNKNOWN)
    }


    override suspend fun createUserProfile(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                displayName = user.username
                photoUri = Uri.parse(user.photoURI)
            }
            val userUUID = auth.currentUser?.uid.toString()
            val userEmail = auth.currentUser?.email.toString()
            val childUpdates = mutableMapOf<String, Any>()
            val databaseReference =
                database.getReference("Profiles").child(userUUID).child("profile")
            childUpdates["/profileUUID/"] = userUUID
            childUpdates["/userEmail/"] = userEmail
            if (user.photoURI != "") {
                childUpdates["/userProfilePictureUrl/"] =
                    user.photoURI
            }
            databaseReference.updateChildren(childUpdates).await()
            val databaseChatReference = database.getReference("Chat_List")
            val chatListItem = UserDomainModel(
                userEmail = userEmail,
                userUUID = userUUID,
                username = user.username,
                userPictureUrl = user.photoURI
            )
            databaseChatReference
                .child(userUUID)
                .setValue(chatListItem)
                .await()

            Result.Success(Unit)
        } catch (error: FirebaseAuthException) {
            when (error.errorCode) {
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        } catch (e: CancellationException) {
            throw e
        }
    }

    override suspend fun isUserAuthorized(): Boolean = auth.currentUser != null

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun observeIsEmailVerified(): Flow<Boolean> =
        flow { auth.currentUser?.isEmailVerified }
}