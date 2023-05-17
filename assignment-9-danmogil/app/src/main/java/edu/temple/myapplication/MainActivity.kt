package edu.temple.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity(), BrowserControlFragment.BrowserControlInterface, PageViewerFragment.PageViewerInterface {


    private val pager: ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    private val browserViewModel : BrowserViewModel by lazy {
        ViewModelProvider(this)[BrowserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = BrowserFragmentStateAdapter(this)

        browserViewModel.getPageIndex().observe(this) {
            pager.setCurrentItem(it, false)
        }

        // Keep track of current page in ViewModel
        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                browserViewModel.setPageIndex(position)

                // Retrieve title from Fragment directly.
                // Exploits FragmentStateAdapter 'f-position' Tag scheme
                supportFragmentManager.findFragmentByTag("f$position")?.run {
                    (this as PageViewerFragment).getPageTitle()?.run {
                        updateTitle(this)
                        browserViewModel.setTitle(position, this)
                    }
                }
            }
        })

        if (browserViewModel.getPageCount() == 0) {
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
        browserViewModel.getPageCount().let { pager.adapter?.notifyItemInserted(it) }
        browserViewModel.getPageCount().let { pager.setCurrentItem(it, true) }
        updateTitle("")
    }

    override fun closePage() {
    }


    // Update or clear Activity title
    override fun updateTitle(title: String) {
        if (title.isNotEmpty())
            supportActionBar?.title = "${getString(R.string.app_name)} - $title"
        else
            supportActionBar?.title = getString(R.string.app_name)
    }

    inner class BrowserFragmentStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        // retrieve page count from ViewModel
        override fun getItemCount() : Int {
            return browserViewModel.getPageCount()
        }

        override fun createFragment(position: Int) = PageViewerFragment.newInstance(position)

    }

}