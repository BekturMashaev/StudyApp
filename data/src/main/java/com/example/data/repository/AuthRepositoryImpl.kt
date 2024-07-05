package com.example.data.repository

import android.net.Uri
import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.AuthRepository
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {
    override suspend fun registerNewUser(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        auth.createUserWithEmailAndPassword(user.email, user.password)
        return try {
            FirebaseAuth.getInstance().addAuthStateListener {
                it.currentUser?.let { firebaseUser ->
                    if (!firebaseUser.isEmailVerified) {
                        firebaseUser.sendEmailVerification()
                    }
                }
            }
            Result.Success(Unit)
        } catch (error: FirebaseAuthException) {
            when (error.errorCode) {
                "email-already-exists" -> Result.Error(DataError.Network.EMAIL_EXISTS)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    override suspend fun createUserProfile(user: UserSignDomainModel): Result<Unit, DataError.Network> {
        val profileUpdates = userProfileChangeRequest {
            displayName = user.username
            photoUri = Uri.parse(user.photoURI.toString())
        }
        return try {
            auth.currentUser?.updateProfile(
                profileUpdates
            )
            Result.Success(Unit)
        } catch (error: FirebaseAuthException) {
            when (error.errorCode) {
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }


    }

    override suspend fun isUserAuthorized(): Boolean = auth.currentUser != null
    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun observeIsEmailVerified(): Flow<Boolean> {
        return flow { auth.currentUser?.isEmailVerified }
    }
}