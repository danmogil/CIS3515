package edu.temple.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

        val textview = findViewById<TextView>(R.id.textview)
        findViewById<Button>(R.id.button).setOnClickListener{
            textview.text = "Button clicked on ${LocalTime.now().format(formatter)}"
        }
    }
}