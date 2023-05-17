package edu.temple.viewpagerinclasssctivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val viewPager : ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    private val button : Button by lazy {
        findViewById(R.id.button)
    }

    private var numberOfPages = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = object: FragmentStateAdapter(this) {
            override fun getItemCount() = numberOfPages

            override fun createFragment(position: Int) = TextFragment.newInstance((position+1).toString());
        }

        button.setOnClickListener {
            numberOfPages++
            viewPager.adapter?.notifyItemInserted(numberOfPages - 1)
            viewPager.setCurrentItem(numberOfPages - 1, true)
        }

    }
}