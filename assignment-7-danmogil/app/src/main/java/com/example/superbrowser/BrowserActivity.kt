package com.example.superbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView

class BrowserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pageViewerFragment = PageViewerFragment()

        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.page_viewer, pageViewerFragment)
                .commit()

    }
}