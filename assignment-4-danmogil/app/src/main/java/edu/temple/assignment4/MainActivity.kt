package edu.temple.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val imageView_lg = findViewById<ImageView>(R.id.imageView_lg)
        val textView = findViewById<TextView>(R.id.textView)

        val images = arrayOf(
            ImageItem(R.drawable.proj_diaz, "Nate Diaz"),
            ImageItem(R.drawable.proj_jones, "John Jones"),
            ImageItem(R.drawable.proj_ferguson, "Tony Ferguson"),
            ImageItem(R.drawable.proj_makhachev, "Islam Makhachev"),
            ImageItem(R.drawable.proj_mcgregor, "Connor McGregor"),
            ImageItem(R.drawable.proj_oliveira, "Charles Oliveira"),
            ImageItem(R.drawable.proj_poirier, "Dustin Poirier"),
            ImageItem(R.drawable.proj_silva, "Anderson Silva"),
            ImageItem(R.drawable.proj_sonnen, "Chael Sonnen"),
            ImageItem(R.drawable.proj_volkanovski, "Alexandar Volkanovski"),
        )

        val callback = {item: ImageItem ->
            imageView_lg.setImageResource(item.id)
            textView.text = item.name
        }

        recyclerView.adapter = ImageAdapter(images, callback)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

    }
}

class ImageAdapter(_imageItems: Array<ImageItem>, _callback: (ImageItem)->Unit) : RecyclerView.Adapter<ImageAdapter.ImageItemViewHolder>() {

    val imageItems = _imageItems
    val callback = _callback

    inner class ImageItemViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
        val imageView = layout.findViewById<ImageView>(R.id.imageView)

        init {
            imageView.setOnClickListener {callback(imageItems[adapterPosition])}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        return ImageItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun getItemCount() = imageItems.size

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.imageView.setImageResource(imageItems[position].id)
    }

}