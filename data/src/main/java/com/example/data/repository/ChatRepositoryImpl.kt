package com.example.data.repository

import com.example.domain.models.UserDomainModel
import com.example.domain.repository.ChatRepository
import com.example.domain.repository.ListOfUsers
import com.example.domain.result.Result
import com.example.domain.result.errortype.DataError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth,
) : ChatRepository {
    override suspend fun loadUsers(): ListOfUsers = callbackFlow {
        try {
            val databaseReference = database.getReference("Chat_List")
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val resultList = mutableListOf<UserDomainModel>()
                    try {
                        for (i in snapshot.children) {
                            val chatPerson = i.getValue(UserDomainModel::class.java)
                            chatPerson?.let {
                                resultList.add(it)
                            }
                        }
                        this@callbackFlow.trySendBlocking(Result.Success(resultList))
                    } catch (e: Exception) {
                        this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.UNKNOWN))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.EMAIL_EXISTS))
                }
            }
            )
            awaitClose {
                channel.close()
                cancel()
            }
        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.UNKNOWN))
        }
    }
    override suspend fun startMessagingInChats(
        opponentUUID: String,
        text: String
    ): Flow<Result<Unit, DataError.Network>> =
        callbackFlow {
            val requesterUUID = auth.currentUser?.uid
            val hashMapOfRequesterUUIDAndAcceptorUUID = hashMapOf<String, String>()
            hashMapOfRequesterUUIDAndAcceptorUUID[requesterUUID!!] = opponentUUID
            val messageUUID = UUID.randomUUID().toString()
            val gson = Gson()
            val requesterUUIDAndAcceptorUUID =
                gson.toJson(hashMapOfRequesterUUIDAndAcceptorUUID)
            try {
                database.reference.child("Chat_Rooms")
                    .child(requesterUUIDAndAcceptorUUID).child(messageUUID)
                    .setValue(text)
                    .await()
                trySend(Result.Success(Unit))
                awaitClose {
                    channel.close()
                    cancel()
                }
            } catch (e: Exception) {
                trySend(Result.Error(DataError.Network.UNKNOWN))
            }
        }

    override suspend fun observeMessagingInChats(opponentUUID: String): Flow<Result<List<String>, DataError.Network>> =
        callbackFlow {
            try {
                val currentUserUUID = auth.currentUser?.uid
                val hashMapOfRequesterUUIDAndAcceptorUUID = hashMapOf<String, String>()
                hashMapOfRequesterUUIDAndAcceptorUUID[currentUserUUID!!] = opponentUUID
                val gson = Gson()
                val requesterUUIDAndAcceptorUUID =
                    gson.toJson(hashMapOfRequesterUUIDAndAcceptorUUID)
                val databaseReference =
                    database.getReference("Chat_Rooms").child(requesterUUIDAndAcceptorUUID)
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val resultList = mutableListOf<String>()
                        try {
                            for (i in snapshot.children) {
                                val chatMessagePerson = i.getValue(String::class.java)
                                chatMessagePerson?.let {
                                    resultList.add(it)
                                }
                            }
                            this@callbackFlow.trySendBlocking(Result.Success(resultList))
                        } catch (e: Exception) {
                            this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.UNKNOWN))
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.EMAIL_EXISTS))
                    }
                }
                )
                awaitClose {
                    channel.close()
                    cancel()
                }
            } catch (e: Exception) {
                this@callbackFlow.trySendBlocking(Result.Error(DataError.Network.UNKNOWN))
            }
        }
}