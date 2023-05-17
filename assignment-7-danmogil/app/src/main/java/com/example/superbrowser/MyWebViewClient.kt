package com.example.superbrowser

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MyWebViewClient(editText: EditText) : WebViewClient() {

    val editText = editText

    @Override
    override fun onLoadResource(view: WebView?, url: String?) {
        editText.setText(url)
    }
}