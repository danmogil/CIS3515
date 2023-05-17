package edu.temple.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        findViewById<TextView>(R.id.display_textView).text = intent.getStringExtra(IMAGE_NAME_KEY)
        findViewById<ImageView>(R.id.display_imageView).setImageResource(intent.getIntExtra(IMAGE_ID_KEY, R.drawable.proj_diaz))

        findViewById<Button>(R.id.button).setOnClickListener {
            finish()
        }
    }
}