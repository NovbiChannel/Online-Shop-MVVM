package com.example.onlineshop.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onlineshop.databinding.FragmentSignInPageBinding
import com.example.onlineshop.other.Constant.DEFAULT_WEB_CLIENT_ID
import com.example.onlineshop.ui.MainActivity
import com.example.onlineshop.ui.viewmodels.AuthViewModel
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.internal.ClientIdentity
import com.google.android.gms.common.internal.ClientSettings
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment: Fragment() {

    private var _binding: FragmentSignInPageBinding? = null
    private val binding get() = _binding!!
    lateinit var launcher: ActivityResultLauncher<Intent>

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val result = account.getResult(ApiException::class.java)
                    val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                    viewModel.googleSignIn(credentials)
                } catch (it: ApiException) {
                    Log.d("Auth", "Error: ${it.message}")
                }
            }

        binding.llGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(DEFAULT_WEB_CLIENT_ID)
                .build()
            val googleSignInClient = GoogleSignIn.getClient(binding.root.context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }
    }
}