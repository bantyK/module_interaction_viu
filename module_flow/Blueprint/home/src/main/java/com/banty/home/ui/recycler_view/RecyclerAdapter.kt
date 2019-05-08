package com.banty.home.ui.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banty.core.model.Clip
import com.banty.home.R
import com.banty.home.ui.ClipClickListener
import com.bumptech.glide.Glide

/**
 * Created by Banty on 2019-05-02.
 */
class RecyclerAdapter(
        private val context: Context,
        private val clipList: List<Clip>,
        private val clipClickListener: ClipClickListener) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clipList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(context)
                .load(clipList[position].thumbUrl)
                .into(holder.imageView)

        holder.textView.text = clipList[position].title

        holder.imageView.setOnClickListener {
            clipClickListener.clipClicked(clipList[position])
        }
    }
}