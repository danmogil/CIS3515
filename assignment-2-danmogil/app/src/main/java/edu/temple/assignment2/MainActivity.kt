package edu.temple.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.Program_spinner)

        // Populate spinner
        ArrayAdapter.createFromResource(this, R.array.programs, android.R.layout.simple_dropdown_item_1line).also {
            spinner.adapter = it
        }

    }

    fun validate_inputs(view: View) {

        val name_editText = findViewById<EditText>(R.id.Name_editText)
        val email_editText = findViewById<EditText>(R.id.Email_editText)
        val password_editText = findViewById<EditText>(R.id.Password_editText)
        val confirm_editText = findViewById<EditText>(R.id.Confirm_editText)

        var isError = false

        // Validate isEmpty
        arrayOf(name_editText, email_editText, password_editText, confirm_editText).forEach { x ->
            run {
                if (x.text.toString().toString() == ""
                ) {
                    x.setError("This field is required!")
                    isError = true
                }
            }
        }

        // Validate spinner
        findViewById<Spinner>(R.id.Program_spinner).apply {
            if(selectedItemPosition == 0) {
                Toast.makeText(this@MainActivity, "Select a Program!", Toast.LENGTH_LONG).show()
                isError = true
            }
        }

        // Validate passwords match
        if(confirm_editText.text.toString().length != 0 && password_editText.text.toString() != confirm_editText.text.toString()) {
            confirm_editText.setError("Passwords must match!")
            isError = true
        }

        if(!isError) {
            findViewById<TextView>(R.id.textView).text = "Welcome ${name_editText.text.trim()}"
        }

    }
}