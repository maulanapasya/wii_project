package com.android.warmindo_inspirasi_indonesia.manager

import android.content.Context
import android.widget.Toast
import com.android.warmindo_inspirasi_indonesia.model.UserProfile
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

object UserManager {
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun createOrUpdateUserProfile(user:FirebaseUser, context: Context){
        val userProfile = UserProfile(
            userId = user.uid,
            userName = user.displayName ?: "User-${user.uid}",
            email = user.email ?: "",
            //kurang picture profile, nanti dulu
        )

        firestore.collection("users").document(user.uid)
            .set(userProfile)
            .addOnSuccessListener {
                Toast.makeText(context, "Profil pengguna berhasil diperbarui", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                e -> Toast.makeText(context, "Profil pengguna gagal diperbarui: ${e.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
    }
}