package com.example.tamagochi

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tamagochi.R.id.doggie



class APP : AppCompatActivity() {

    // Define initial attributes for the pet: happiness, hunger, and cleanliness
    private val initialPetAttributes = arrayOf(100, 0, 100)

    // Clone the initial attributes for the pet
    private var petAttributes = initialPetAttributes.clone()

    // Declaration of UI elements
    private lateinit var playButton: Button
    private lateinit var cleanButton: Button
    private lateinit var petHungerTextView: TextView
    private lateinit var feedButton: Button
    private lateinit var petHappinessTextView: TextView
    private lateinit var petCleanlinessTextView: TextView
    private lateinit var petView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        // Initialize UI elements
        playButton = findViewById(R.id.playbut)
        cleanButton = findViewById(R.id.cleanbut)
        feedButton = findViewById(R.id.feedbut)
        petHappinessTextView = findViewById(R.id.happinessmeter)
        petCleanlinessTextView = findViewById(R.id.cleanmeter)
        petHungerTextView = findViewById(R.id.hungermeter)
        petView = findViewById(doggie)


        updateUI()

        // Set click listeners for action buttons
        feedButton.setOnClickListener {
            performActionWithDelay(Action.FEED) // Call performAction function when feedButton is clicked
        }

        cleanButton.setOnClickListener {
            performActionWithDelay(Action.CLEAN) // Call performAction function when cleanButton is clicked
        }

        playButton.setOnClickListener {
            performActionWithDelay(Action.PLAY) // Call performAction function when playButton is clicked
        }
    }

    // Update UI with the current pet's attributes
    private fun updateUI() {
        petHappinessTextView.text = "Happiness: ${petAttributes[0]}" // Update happiness text view
        petHungerTextView.text = "Hunger: ${petAttributes[1]}" // Update hunger text view
        petCleanlinessTextView.text = "Cleanliness: ${petAttributes[2]}" // Update cleanliness text view

        updatePetImage() // Update pet's image
    }

    // Update pet's image based on its status
    private fun updatePetImage() {
        // Set different images based on the pet's status
        val petHappiness = petAttributes[0]
        val petHunger = petAttributes[1]
        val petCleanliness = petAttributes[2]

        val imageResource = when {
            petHappiness > 70 && petHunger < 30 && petCleanliness > 70 -> R.drawable.dab
            petHappiness < 30 && petHunger > 70 && petCleanliness < 30 -> R.drawable.sad
            petHunger > 70 -> R.drawable.hunger
            petCleanliness < 30 -> R.drawable.dirty
            else -> R.drawable.curios
        }

        petView.setImageResource(imageResource)
    }

    // Perform the specified action on the pet after a delay of 0.5 seconds
    private fun performActionWithDelay(action: Action) {
        when (action) {
            Action.FEED -> {
                petView.setImageResource(R.drawable.eating) // Change pet image to feed action
                android.os.Handler().postDelayed({
                    feedPet() // Perform feed action after 0.5 seconds delay
                }, 500) // Delay of 500 milliseconds (0.5 seconds)
            }
            Action.CLEAN -> {
                petView.setImageResource(R.drawable.bath) // Change pet image to clean action
                android.os.Handler().postDelayed({
                    cleanPet() // Perform clean action after 0.5 seconds delay
                }, 500) // Delay of 500 milliseconds (0.5 seconds)
            }
            Action.PLAY -> {
                petView.setImageResource(R.drawable.playball) // Change pet image to play action
                android.os.Handler().postDelayed({
                    playWithPet() // Perform play action after 0.5 seconds delay
                }, 500) // Delay of 500 milliseconds (0.5 seconds)
            }
        }
    }


    // Feed the pet and update its attributes
    private fun feedPet() {
        petAttributes[1] -= 20
        if (petAttributes[1] < 0) petAttributes[1] = 0
        petAttributes[0] += 10
        if (petAttributes[0] > 100) petAttributes[0] = 100

        updateUI()
        showToast("You fed your pet!")
    }

    // Clean the pet and update its attributes
    private fun cleanPet() {
        petAttributes[2] += 20
        if (petAttributes[2] > 100) petAttributes[2] = 100

        updateUI()
        showToast("You cleaned your pet!")
    }

    // Play with the pet and update its attributes
    private fun playWithPet() {
        petAttributes[0] -= 10
        if (petAttributes[0] < 0) petAttributes[0] = 0
        petAttributes[1] += 10
        if (petAttributes[1] > 100) petAttributes[1] = 100
        petAttributes[2] -= 10
        if (petAttributes[2] < 0) petAttributes[2] = 0

        updateUI()
        showToast("You played with your pet!")
    }

    // Display toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Enum class to represent different actions that can be performed on the pet
    enum class Action {
        FEED, CLEAN, PLAY
    }

}



