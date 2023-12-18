package com.android.warmindo_inspirasi_indonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.warmindo_inspirasi_indonesia.manager.UserManager
import com.android.warmindo_inspirasi_indonesia.util.AuthenticationUtil
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val loginButton = findViewById<Button>(R.id.button_login)
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        // Autentikasi dengan Firebase
        AuthenticationUtil.signIn(email, password, this){

                //Navigasi ke halaman utama
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}
