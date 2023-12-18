package com.android.warmindo_inspirasi_indonesia

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat


class TransactionActivity : AppCompatActivity() {
    private fun updateUIWithUserName(userName: String) {
        val welcomeTextView = findViewById<TextView>(R.id.welcome_text)
        val fullText = "Selamat bekerja,\n$userName"
        val spannable = SpannableString(fullText)

        // Dapatkan font dari resources
        val customFont: Typeface = ResourcesCompat.getFont(this, R.font.sequel_sans_bold_head)!!

        // Terapkan font pada nama pengguna
        val userNameStart = fullText.indexOf(userName)
        spannable.setSpan(
            StyleSpan(customFont.style),
            userNameStart,
            userNameStart + userName.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Set teks ke TextView
        welcomeTextView.text = spannable
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        //inisialisasi nama username yg dipanggil dari fungsi pada DashboardActivity
        val userName = intent.getStringExtra("EXTRA_USER_NAME") ?: "User"

       //update UI dengan username
        updateUIWithUserName(userName)
    }
}