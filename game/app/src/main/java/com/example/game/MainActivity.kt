package com.example.game

import FlyingMonkeyView // Import the FlyingMonkeyView class
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var gameView: FlyingMonkeyView // Declare a variable to hold the game view
    private val handler = Handler() // Handler to schedule view updates
    private val interval: Long = 30 // Interval for view updates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the game view
        gameView = FlyingMonkeyView(this)

        // Set the game view as the content view of the activity
        setContentView(gameView)

        // Create a timer to schedule periodic view updates
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                // Post an invalidate request to the game view's handler for redraw
                handler.post { gameView.invalidate() }
            }
        }, 0, interval) // Schedule the task to run every 'interval' milliseconds
    }
}
