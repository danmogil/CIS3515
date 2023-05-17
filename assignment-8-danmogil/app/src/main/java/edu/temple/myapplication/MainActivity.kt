package edu.temple.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val pageDisplay : ViewPager2 by lazy {
        findViewById(R.id.page_display)
    }

    private val viewModel : PageViewModel by lazy {
        ViewModelProvider(this)[PageViewModel::class.java]
    }

    private var numPages = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.currentPageTitle.observe(this@MainActivity) {
            title = it
        }

        viewModel.numPages.observe(this@MainActivity) {
            numPages = it
            pageDisplay.adapter?.notifyItemInserted(numPages-1)
            pageDisplay.setCurrentItem(numPages-1, true)
        }

        pageDisplay.adapter = object: FragmentStateAdapter(this) {
            override fun getItemCount() = numPages

            override fun createFragment(position: Int) = PageViewerFragment.newInstance(position)
        }

        pageDisplay.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                supportFragmentManager.findFragmentByTag("f$position")
                    ?.view
                    ?.findViewById<WebView>(R.id.webView)
                    ?.title.run {
                        if(this == "") viewModel.setDefaultTitle()
                        else this?.let { viewModel.setCurrentPageTitle(it) }
                    }
            }
        })

    }
}