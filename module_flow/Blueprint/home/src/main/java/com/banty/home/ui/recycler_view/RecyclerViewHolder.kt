package com.banty.home.ui.recycler_view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.banty.home.R

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView = itemView.findViewById<ImageView>(R.id.imageView_recycler_item)
    val textView = itemView.findViewById<TextView>(R.id.textView_recycler_item)
}
