package edu.temple.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class PageListFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView

    private val browserViewModel : BrowserViewModel by lazy {
        ViewModelProvider(requireActivity())[BrowserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_page_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        val myAdapter = PageListAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }

        browserViewModel.getPages().observe(viewLifecycleOwner) {
            myAdapter.setArr(it)
        }

        return view
    }

    inner class PageListAdapter() : RecyclerView.Adapter<PageListAdapter.PageListViewHolder>() {

        private var arr = listOf<String>()

        inner class PageListViewHolder(_textView : TextView) : RecyclerView.ViewHolder(_textView) {
            val textView = _textView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageListViewHolder {
            return PageListViewHolder(
                TextView(parent.context).apply {
                    setPadding(20)
                }
            )
        }

        override fun getItemCount() = arr.size

        override fun onBindViewHolder(holder: PageListViewHolder, position: Int) {
            holder.textView.apply {
                text = if(arr[position] == "") "_blank" else arr[position]
                setOnClickListener{
                    browserViewModel.setPageIndex(position)
                }
            }
        }

        fun setArr(newArr : List<String>) {
            arr = newArr
            notifyDataSetChanged()
        }

    }

}