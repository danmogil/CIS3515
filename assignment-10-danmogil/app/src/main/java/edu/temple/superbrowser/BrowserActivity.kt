package edu.temple.superbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class BrowserActivity : AppCompatActivity(), BrowserControlFragment.BrowserControlInterface, PageViewerFragment.PageViewerInterface, PageListFragment.PageListInterface {

    private val pager: ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    private val browserViewModel : BrowserViewModel by lazy {
        ViewModelProvider(this)[BrowserViewModel::class.java]
    }

    private val internalFilename = "bookmarks"
    private lateinit var file : File

    private val sb = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        file = File(filesDir, internalFilename)
        if(file.exists()) {
            val br = BufferedReader(FileReader(file))
            var line: String?
            while(br.readLine().also { line = it } != null) {
                sb.append(line)
                sb.append("\n")
            }
            br.close()
        }

        pager.adapter = BrowserFragmentStateAdapter(this)

        // Move to previous page index
        pager.setCurrentItem(browserViewModel.currentPageIndex, false)

        // Keep track of current page in ViewModel
        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                browserViewModel.currentPageIndex = position

                // Retrieve title from Fragment directly.
                // Exploits FragmentStateAdapter 'f-position' Tag scheme
                supportFragmentManager.findFragmentByTag("f$position")?.run {
                    (this as PageViewerFragment).getPageTitle()?.run {
                        updateTitle(position, this)
                    }
                }
            }
        })


        if (browserViewModel.getCurrentPageCount() == 0) {
            addPage()
        }

    }

    /**
     * Add a new page to the list by
     * - increasing page count
     * - notifying adapter
     * - switching ViewPager to current page
     * - clearing Activity title
     */
    override fun addPage() {
        browserViewModel.addNewPage()
        pager.run {
            adapter?.notifyItemInserted(browserViewModel.getCurrentPageCount())
            setCurrentItem(browserViewModel.getCurrentPageCount(), true)
        }

        updateTitle(browserViewModel.currentPageIndex,"")
    }

    override fun sharePage() {
        val url = browserViewModel.getCurrentURL()

        // Page must be loaded!
        if(url == "") {
            Toast.makeText(this, "Unable to share empty page!", Toast.LENGTH_SHORT)
                .show()
        }
        else startActivity(
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
            }
        )
    }

    override fun viewBookmarks() {
        startActivityForResult(
            Intent(this, BookmarkActivity::class.java), 1
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            supportFragmentManager.findFragmentByTag("f${browserViewModel.currentPageIndex}")?.run {
                (this as PageViewerFragment).webView.loadUrl(data?.getStringExtra("data")!!)
            }
        }
    }

    override fun addBookmark() {
        val url = browserViewModel.getCurrentURL()

        var toastMSG = "Unable to bookmark empty page!"

        if(url != "") {

            if(sb.contains(url)) {
                toastMSG = "Bookmark already exists!"
            } else {
                val title = browserViewModel.pageTitles[browserViewModel.currentPageIndex]

                val outputStream = FileOutputStream(file)
                sb.append("${url};${title}\n")
                outputStream.write(sb.toString().toByteArray())
                outputStream.close()

                toastMSG = "Bookmark saved!"
            }
        }

        Toast.makeText(this, toastMSG, Toast.LENGTH_SHORT)
            .show()
    }

    override fun closePage() {
    }


    // Update or clear Activity title
    override fun updateTitle(pageId: Int, title: String) {
        if (pageId == browserViewModel.currentPageIndex) {
            if (title.isNotEmpty())
                supportActionBar?.title = "${getString(R.string.app_name)} - $title"
            else
                supportActionBar?.title = getString(R.string.app_name)
        }
    }

    inner class BrowserFragmentStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        // retrieve page count from ViewModel
        override fun getItemCount() : Int {
            return browserViewModel.getCurrentPageCount()
        }

        override fun createFragment(position: Int) = PageViewerFragment.newInstance(position)

    }

    override fun pageSelected(pageIndex: Int) {
        pager.setCurrentItem(pageIndex, true)
    }

}