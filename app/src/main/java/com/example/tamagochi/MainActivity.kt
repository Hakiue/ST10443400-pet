package com.example.tamagochi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.enter)

        // Set an OnClickListener on the button
        button.setOnClickListener {
            // Create an Intent to start APP
            val intent = Intent(this, APP::class.java)
            startActivity(intent)
        }
    }
}