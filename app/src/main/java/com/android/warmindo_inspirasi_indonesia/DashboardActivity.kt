package com.android.warmindo_inspirasi_indonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.warmindo_inspirasi_indonesia.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class DashboardActivity : AppCompatActivity() {
    //Insialisasi FirebaseFirestore
    private val db by lazy {
        FirebaseFirestore.getInstance()
    }

    private fun updateUIWithUserProfile(userProfile: UserProfile){
        val welcomeTextView = findViewById<TextView>(R.id.welcome_text)
        welcomeTextView.text = getString(R.string.welcome_message, userProfile.userName)
    }

    private fun loadUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val userId = it.uid
            val docRef = db.collection("users").document(userId)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                userProfile?.let { profile -> updateUIWithUserProfile(profile) }
            }.addOnFailureListener {
                e -> Toast.makeText(this,"Gagal memuat profil pengguna: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        loadUserProfile()

        val mulaiShiftButton = findViewById<Button>(R.id.mulai_shift_button)
        mulaiShiftButton.setOnClickListener {
            //intent memulai ke transaction activity
            val intent = Intent(this,TransactionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}