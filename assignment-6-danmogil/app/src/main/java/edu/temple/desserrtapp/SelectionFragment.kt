package edu.temple.desserrtapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectionFragment : Fragment() {

    private lateinit var items : Array<Item>

    lateinit var itemViewModel : ItemViewModel
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            items = it.getParcelableArray(MainActivity.IMAGE_ARRAY_KEY, Item::class.java) as Array<Item>
        }

        itemViewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        itemViewModel.setItems(items)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(context, 2)

            itemViewModel.getItems().observe(requireActivity()) {
                adapter = ImageAdapter(items) { item->
                    itemViewModel.setSelectedItem(item)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(images: Array<Item>) =
            SelectionFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(MainActivity.IMAGE_ARRAY_KEY, images)
                }
            }
    }
}