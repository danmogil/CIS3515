package edu.temple.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

class BrowserControlFragment : Fragment() {

    private val addPageButton : ImageButton by lazy {
        requireView().findViewById(R.id.addPageButton)
    }

    private val pageDisplay : ViewPager2 by lazy {
        requireActivity().findViewById(R.id.page_display)
    }

    private val viewModel : PageViewModel by lazy {
        ViewModelProvider(requireActivity())[PageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browser_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPageButton.setOnClickListener {
            viewModel.addPage()
        }
    }

}