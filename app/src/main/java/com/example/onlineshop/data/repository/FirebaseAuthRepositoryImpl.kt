package com.example.onlineshop.data.repository

import com.example.onlineshop.domain.repository.FirebaseAuthRepository
import com.example.onlineshop.other.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseAuthRepository {
    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(data = result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }


}