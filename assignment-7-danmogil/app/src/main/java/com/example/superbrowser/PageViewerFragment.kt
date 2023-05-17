package com.example.superbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment

class PageViewerFragment : Fragment() {

    lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_viewer, container, false)

        val urlEditText = view.findViewById<EditText>(R.id.urlEditText)
        webView = view.findViewById(R.id.webView)

        if(savedInstanceState != null)
            webView.restoreState(savedInstanceState)
        else
            webView.webViewClient = MyWebViewClient(urlEditText)

        view.findViewById<AppCompatImageButton>(R.id.goButton).setOnClickListener {
            webView.loadUrl(urlEditText.text.toString())
        }

        view.findViewById<AppCompatImageButton>(R.id.backButton).setOnClickListener {
            webView.goBack()
        }

        view.findViewById<AppCompatImageButton>(R.id.nextButton).setOnClickListener {
            webView.goForward()
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

}