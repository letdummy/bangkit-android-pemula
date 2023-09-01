package com.sekalisubmit.moviemu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val btnHome: Button = findViewById(R.id.home_button)

        btnHome.setOnClickListener {
            val linkGitHub = "https://github.com/letdummy"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkGitHub))
            startActivity(browserIntent)
        }
    }
}
