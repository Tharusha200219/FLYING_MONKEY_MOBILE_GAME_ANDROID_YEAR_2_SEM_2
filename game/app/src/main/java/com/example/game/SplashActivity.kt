package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Find the playButton by ID
        val playButton: Button = findViewById(R.id.playButton)

        // Set click listener for the Play button
        playButton.setOnClickListener {
            // Navigate to MainActivity when the button is clicked
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
        }

        val howtoplay: Button = findViewById(R.id.howtoplay)

        // Set click listener for the Play button
        howtoplay.setOnClickListener {
            // Navigate to MainActivity when the button is clicked
            val mainIntent = Intent(this@SplashActivity, HowToPlayActivity::class.java)
            startActivity(mainIntent)
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
