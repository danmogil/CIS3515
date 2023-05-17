package edu.temple.assignment3

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colors: Array<String> = arrayOf("Select a color", "Lime", "Blue", "Cyan", "Red", "Grey", "Green", "Magenta", "White", "Yellow", "Purple")

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ColorAdapter(this, colors)
        spinner.setBackgroundColor(Color.WHITE)

        val layout = findViewById<ConstraintLayout>(R.id.layout)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0)
                    layout.setBackgroundColor(Color.parseColor(colors[position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }
}

class ColorAdapter(_context: Context, _colors: Array<String>) : BaseAdapter() {

    val context = _context
    val colors = _colors

    override fun getCount() = colors.size

    override fun getItem(position: Int) = colors[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView = (if(convertView == null) {
            TextView(context).apply {
                textSize = 22.0F
                setPadding(20,20,0,20)
                if(position == 0)
                    setBackgroundColor(Color.WHITE)
            }
        } else convertView as TextView).apply {
            text = colors[position]
        }
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getDropDownView(position, convertView, parent).apply {
            setBackgroundColor(if(position != 0) Color.parseColor(colors[position]) else Color.WHITE)
        }
    }

}