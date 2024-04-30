package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Import necessary classes and resources
import com.example.game.MainActivity
import com.example.game.R


class GameOverActivity : AppCompatActivity() {

    // Declare variables for UI elements
    private lateinit var startGameAgain: Button
    private lateinit var displayScore: TextView
    private lateinit var score: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over) // Set layout for the activity

        // Get score from intent extra
        score = intent.extras?.get("score").toString()

        // Initialize UI elements
        startGameAgain = findViewById(R.id.play_again_btn)
        displayScore = findViewById(R.id.displayScore)

        // Get current and high score
        val currentScore = score.toInt()
        val highScore = ScoreManager.loadHighScore(this)

        // Update UI with score information
        if (currentScore > highScore) {
            // If current score is higher than high score, update high score
            ScoreManager.saveHighScore(this, currentScore)
            displayScore.text = "New High Score: $score"
        } else {
            // If current score is not higher, display both scores
            displayScore.text = "Score: $score\nHigh Score: $highScore"
        }

        // Set click listener for the "Play Again" button
        startGameAgain.setOnClickListener {
            // Navigate to SplashActivity when button is clicked
            val mainIntent = Intent(this@GameOverActivity, SplashActivity::class.java)
            startActivity(mainIntent)
        }
    }
}
