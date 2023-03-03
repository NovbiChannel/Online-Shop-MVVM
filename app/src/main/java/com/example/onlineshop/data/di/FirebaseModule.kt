package com.example.onlineshop.data.di

import com.example.onlineshop.data.repository.FirebaseAuthRepositoryImpl
import com.example.onlineshop.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}