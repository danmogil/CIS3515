package edu.temple.superbrowser

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader


class BookmarkActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val closeButton: ImageButton by lazy {
        findViewById(R.id.closeButton)
    }

    private val browserViewModel : BrowserViewModel by lazy {
        ViewModelProvider(this)[BrowserViewModel::class.java]
    }

    private val internalFilename = "bookmarks"
    private lateinit var file : File

    private val data = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        file = File(filesDir, internalFilename)
        if(file.exists()) {
            val br = BufferedReader(FileReader(file))
            var line: String?
            while(br.readLine().also { line = it } != null)
                data.add(line!!)
            br.close()
        }
        Log.d("test", data.size.toString())
        val myAdapter = BookmarkAdapter()

        recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        closeButton.setOnClickListener {
            finish()
        }

    }

    override fun onStop() {
        super.onStop()

        val outputStream = FileOutputStream(file)
        val sb = StringBuilder()

        for(l in data) {
            sb.append(l)
            sb.append("\n")
        }

        outputStream.write(sb.toString().toByteArray())
        outputStream.close()
    }

    inner class BookmarkAdapter() : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

        inner class ViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
            val textView = layout.findViewById<TextView>(R.id.bookmark_textView)
            val removeButton = layout.findViewById<ImageButton>(R.id.removeButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val parts = data[position].split(';')
            val url = parts[0]
            val title = parts[1]

            holder.textView.text = title

            holder.textView.setOnClickListener {
                setResult(RESULT_OK, Intent().apply {
                    putExtra("data", url)
                })
                finish()
            }

            holder.removeButton.setOnClickListener {
                AlertDialog.Builder(this@BookmarkActivity)
                    .setTitle("Delete bookmark")
                    .setMessage("Are you sure you want to delete this bookmark?")
                    .setPositiveButton(
                        "Yes"
                    ) { dialog, which ->
                        data.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    .setNegativeButton("No", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }

    }
}