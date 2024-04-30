package com.example.game

import android.content.Context


object ScoreManager {

    // Constants for SharedPreferences
    private const val PREFS_NAME = "MyPrefs" // Name of the SharedPreferences file
    private const val HIGH_SCORE_KEY = "high_score" // Key for storing the high score

    // Function to save the high score to SharedPreferences
    fun saveHighScore(context: Context, highScore: Int) {
        // Get SharedPreferences instance
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Get editor for SharedPreferences
        val editor = sharedPreferences.edit()
        // Store the high score using the specified key
        editor.putInt(HIGH_SCORE_KEY, highScore)
        // Apply changes
        editor.apply()
    }

    // Function to load the high score from SharedPreferences
    fun loadHighScore(context: Context): Int {
        // Get SharedPreferences instance
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Retrieve the high score using the specified key, defaulting to 0 if not found
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    }
}
