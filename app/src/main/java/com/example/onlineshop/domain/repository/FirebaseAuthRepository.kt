package com.example.onlineshop.domain.repository

import com.example.onlineshop.other.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {

    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}