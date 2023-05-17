package com.example.coroutineactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val timerTextView: TextView by lazy {
        findViewById(R.id.timerTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            CoroutineScope(Job() + Dispatchers.Main).launch {
                repeat(100) {x ->
                    timerTextView.text = x.toString()
                    delay(1000)
                }
            }
        }


    }
}