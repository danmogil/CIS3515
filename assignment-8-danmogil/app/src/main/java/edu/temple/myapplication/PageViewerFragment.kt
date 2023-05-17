package edu.temple.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

private const val ID_KEY = "KEY"

class PageViewerFragment() : Fragment() {

    private lateinit var webView: WebView
    private lateinit var goButton: ImageButton
    private lateinit var bkButton: ImageButton
    private lateinit var fwdButton: ImageButton
    private lateinit var urlEditText: EditText

    private var fID : Int = -1
    private val viewModel : PageViewModel by lazy {
        ViewModelProvider(this)[PageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fID = it.getInt(ID_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.currentPageTitle.observe(viewLifecycleOwner) {
            requireActivity().title = it
        }

        return inflater.inflate(R.layout.fragment_page_viewer, container, false).apply {
            webView = findViewById(R.id.webView)
            goButton = findViewById(R.id.goButton)
            bkButton = findViewById(R.id.bkButton)
            fwdButton = findViewById(R.id.fwdButton)
            urlEditText = findViewById(R.id.urlEditText)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Enable Javascript for proper functioning websites
        webView.settings.javaScriptEnabled = true;

        // Provision WebViewClient implementation that retrieves current page URL
        webView.webViewClient = object: WebViewClient () {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.run {
                    urlEditText.setText(this)
                    if(requireActivity().findViewById<ViewPager2>(R.id.page_display).currentItem == fID)
                        viewModel.setCurrentPageTitle(webView.title!!)
                }
            }
        }

        // Restore previous web state if present
        savedInstanceState?.run {
            webView.restoreState(this)
        }


        goButton.setOnClickListener {
            urlEditText.setText(cleanUrl(urlEditText.text.toString()))
            webView.loadUrl(urlEditText.text.toString())
        }

        bkButton.setOnClickListener {
            webView.goBack()
        }

        fwdButton.setOnClickListener {
            webView.goForward()
        }

    }

    // Helper function to format malformed URLs
    private fun cleanUrl(url: String) : String {
        return if (url.startsWith("http"))
            url
        else
            "https://$url"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Store current web state
        webView.saveState(outState)
    }

    companion object {
        @JvmStatic
        fun newInstance(_id: Int) =
            PageViewerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_KEY, _id)
                }
            }
    }

}