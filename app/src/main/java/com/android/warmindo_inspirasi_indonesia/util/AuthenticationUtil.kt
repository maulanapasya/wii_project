package com.android.warmindo_inspirasi_indonesia.util

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthenticationUtil {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun signIn(email: String, password: String, context: Context, onSuccess: (FirebaseUser) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task -> if (task.isSuccessful){
                auth.currentUser?.let{currentUser -> onSuccess(currentUser)}
            } else {
                Toast.makeText(context, "Login failed. Check your email and password.", Toast.LENGTH_SHORT).show()
            }
            }
    }
}