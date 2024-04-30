package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.game.R

class HowToPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)

        // Find the back button by ID
        val backButton: Button = findViewById(R.id.backButton)

        // Set click listener for the back button
        backButton.setOnClickListener {
            // Navigate back to MainActivity when the button is clicked
            val mainIntent = Intent(this@HowToPlayActivity, SplashActivity::class.java)
            startActivity(mainIntent)
        }
    }
}
