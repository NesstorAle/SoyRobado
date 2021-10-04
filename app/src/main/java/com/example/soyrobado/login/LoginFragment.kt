package com.example.soyrobado.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.soyrobado.databinding.FragmentLoginBinding
import com.example.soyrobado.firebase.FirebaseRepository
import com.example.soyrobado.getViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val model: LoginViewModel by lazy {
        getViewModel { LoginViewModel(FirebaseRepository()) }
    }
    private lateinit var googleSignInClient:GoogleSignInClient
    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("AIzaSyDAbrItYo7Cf7bVROAI9YHogR48m5ljdOM")
        .requestEmail()
        .build()

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 101)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        var auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                 //   val user = auth.currentUser
                  //  updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                   // updateUI(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        observers()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.apply {

            login.setOnClickListener {
                if (!TextUtils.isEmpty(username.text.toString())) {
                    if (!TextUtils.isEmpty(password.text.toString())) {
                        model.login(username.text.toString(), password.text.toString())
                    } else {
                        password.error = "Requerido"
                    }
                } else {
                    username.error = "Requerido"
                }

            }
            buttonLoginGoogle.setOnClickListener {
                signIn()
            }
        }

        return binding.root

    }

    private fun observers() {
        model.loginMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })
    }


}