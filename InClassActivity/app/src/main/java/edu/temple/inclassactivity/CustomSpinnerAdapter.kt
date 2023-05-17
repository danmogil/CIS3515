package edu.temple.inclassactivity

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class CustomSpinnerAdapter(private val context: Context, private val items: Array<Int>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
        (convertView
        ?: ImageView(context).apply {
            layoutParams = ViewGroup.LayoutParams(200, 200)
        }).apply {
            (this as ImageView).setImageResource(items[position])
        }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?) =
        getView(position, convertView, parent).apply { layoutParams.height = 700; layoutParams.width = 700 }

}