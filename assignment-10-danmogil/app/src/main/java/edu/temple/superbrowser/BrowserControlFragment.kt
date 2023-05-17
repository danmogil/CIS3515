package edu.temple.superbrowser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class BrowserControlFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_browser_control, container, false).apply {
            findViewById<ImageButton>(R.id.addPageButton).setOnClickListener{(requireActivity() as BrowserControlInterface).addPage()}
            findViewById<ImageButton>(R.id.shareButton).setOnClickListener{(requireActivity() as BrowserControlInterface).sharePage()}
            findViewById<ImageButton>(R.id.bookmarkListButton).setOnClickListener{(requireActivity() as BrowserControlInterface).viewBookmarks()}
            findViewById<ImageButton>(R.id.bookmarkButton).setOnClickListener{(requireActivity() as BrowserControlInterface).addBookmark()}
            findViewById<ImageButton>(R.id.closePageButton).setOnClickListener{(requireActivity() as BrowserControlInterface).closePage()}
        }
    }

    interface BrowserControlInterface {
        fun addPage()
        fun sharePage()
        fun viewBookmarks()
        fun addBookmark()
        fun closePage()
    }

}