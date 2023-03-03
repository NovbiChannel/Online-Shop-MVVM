package com.example.onlineshop.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.models.GoogleSignInState
import com.example.onlineshop.domain.repository.FirebaseAuthRepository
import com.example.onlineshop.other.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: FirebaseAuthRepository
): ViewModel(){

    val googleState: MutableLiveData<GoogleSignInState> = MutableLiveData()

    fun googleSignIn(credential: AuthCredential) =
        viewModelScope.launch {
            repository.googleSignIn(credential).collect{ result ->
                when(result) {
                    is Resource.Success -> {
                        googleState.postValue(GoogleSignInState(success = result.data))
                    }
                    is Resource.Loading -> {
                        googleState.postValue(GoogleSignInState(loading = true))
                    }
                    is Resource.Error -> {
                        googleState.postValue(GoogleSignInState(error = result.message!!))
                    }
                }
            }
        }

}