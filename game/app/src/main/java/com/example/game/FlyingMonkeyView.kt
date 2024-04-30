import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.example.game.GameOverActivity // Import the GameOverActivity class
import com.example.game.R

// Define a custom view for the game
class FlyingMonkeyView(context: Context) : View(context) {

    // Variables for monkey images and properties
    private val monkey = arrayOfNulls<Bitmap>(2)
    private var monkeyX = 10
    private var monkeyY: Int = 0
    private var monkeySpeed: Int = 0

    // Variables for canvas dimensions
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0

    // Variables for yellow balls
    private var yellowX: Int = 0
    private var yellowY: Int = 0
    private var yellowSpeed = 15
    private val yellowPaint = Paint()

    // Variables for green balls
    private var greenX: Int = 0
    private var greenY: Int = 0
    private var greenSpeed = 15
    private val greenPaint = Paint()

    // Variables for red balls
    private var redX: Int = 0
    private var redY: Int = 0
    private var redSpeed = 15
    private val redPaint = Paint()

    // Variables for score and life
    private var score: Int = 0
    private var lifeCounterOfFish: Int = 0

    // Variable to track touch
    private var touch = false

    // Variable for background image
    private val backgroundImage: Bitmap

    // Paint object for score display
    private val scorePaint = Paint()

    // Array of life images
    private val life = arrayOfNulls<Bitmap>(2)

    // Flag to indicate game over
    private var isGameOver = false


    init {
        // Load monkey images
        monkey[0] = BitmapFactory.decodeResource(resources, R.drawable.monkey)
        monkey[1] = BitmapFactory.decodeResource(resources, R.drawable.monkey)

        // Load background image
        backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.background)

        // Set up paint for yellow balls
        yellowPaint.color = Color.YELLOW
        yellowPaint.isAntiAlias = false

        // Set up paint for green balls
        greenPaint.color = Color.GREEN
        greenPaint.isAntiAlias = false

        // Set up paint for red balls
        redPaint.color = Color.RED
        redPaint.isAntiAlias = false

        // Set up paint for score display
        scorePaint.color = Color.WHITE
        scorePaint.textSize = 70f
        scorePaint.typeface = Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias = true

        // Load life images
        life[0] = BitmapFactory.decodeResource(resources, R.drawable.hearts)
        life[1] = BitmapFactory.decodeResource(resources, R.drawable.heart_grey)

        // Initialize monkey position, score, and life count
        monkeyY = 550
        score = 0
        lifeCounterOfFish = 3
    }

    // Method to draw on the canvas
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Get canvas dimensions
        canvasWidth = canvas.width
        canvasHeight = canvas.height

        // Draw background image
        canvas.drawBitmap(backgroundImage, 0f, 0f, null)

        // Calculate min and max positions for yellow balls
        val minFishY = monkey[0]!!.height
        val maxFishY = canvasHeight - monkey[0]!!.height * 3

        // Draw monkey based on touch
        if (touch) {
            canvas.drawBitmap(monkey[1]!!, monkeyX.toFloat(), monkeyY.toFloat(), null)
        } else {
            canvas.drawBitmap(monkey[0]!!, monkeyX.toFloat(), monkeyY.toFloat(), null)
        }

        // Update position of yellow balls
        yellowX -= yellowSpeed
        if (hitBallChecker(yellowX, yellowY)) {
            score += 10
            yellowX = -100
        }
        if (yellowX < 0) {
            yellowX = canvasWidth + 21
            yellowY = (Math.random() * (maxFishY - minFishY) + minFishY).toInt()
        }
        canvas.drawCircle(yellowX.toFloat(), yellowY.toFloat(), 25f, yellowPaint)

        // Update position of green balls
        greenX -= greenSpeed
        if (hitBallChecker(greenX, greenY)) {
            score += 20
            greenX = -100
        }
        if (greenX < 0) {
            greenX = canvasWidth + 21
            greenY = (Math.random() * (maxFishY - minFishY) + minFishY).toInt()
        }
        canvas.drawCircle(greenX.toFloat(), greenY.toFloat(), 25f, greenPaint)

        // Update position of red balls
        redX -= redSpeed
        if (hitBallChecker(redX, redY)) {
            redX = -100
            lifeCounterOfFish--

            if (lifeCounterOfFish == 0) {
                isGameOver = true
            }
        }
        if (redX < 0) {
            redX = canvasWidth + 21
            redY = (Math.random() * (maxFishY - minFishY) + minFishY).toInt()
        }
        canvas.drawCircle(redX.toFloat(), redY.toFloat(), 30f, redPaint)

        // Draw score and life
        canvas.drawText("Score : $score", 20f, 60f, scorePaint)
        for (i in 0 until 3) {
            val x = (580 + life[0]!!.width * 1.5 * i).toInt()
            val y = 30

            if (i < lifeCounterOfFish) {
                canvas.drawBitmap(life[0]!!, x.toFloat(), y.toFloat(), null)
            } else {
                canvas.drawBitmap(life[1]!!, x.toFloat(), y.toFloat(), null)
            }
        }

        // Start GameOverActivity if the game is over
        if (isGameOver) {
            val gameOverIntent = Intent(context, GameOverActivity::class.java)
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            gameOverIntent.putExtra("score", score)
            context.startActivity(gameOverIntent)
        }

        // Invalidate the view to redraw it
        invalidate()
    }

    // Method to check if a ball is hit by the monkey
    private fun hitBallChecker(x: Int, y: Int): Boolean {
        return monkeyX < x && x < monkeyX + monkey[0]!!.width && monkeyY < y && y < monkeyY + monkey[0]!!.height
    }

    // Method to handle touch events
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touch = true
                monkeySpeed = 0
                monkeyY = event.y.toInt() - monkey[0]!!.height / 2
            }
            MotionEvent.ACTION_MOVE -> {
                monkeyY = event.y.toInt() - monkey[0]!!.height / 2
            }
            MotionEvent.ACTION_UP -> {
                touch = false
            }
        }
        // Invalidate the view to redraw it
        invalidate()
        return true
    }
}
