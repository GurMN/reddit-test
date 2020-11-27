package com.test.redditproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.redditproject.R
import com.test.redditproject.api.DataModel

class MainAdapter(private val topList: MutableList<DataModel> = mutableListOf()) :
    RecyclerView.Adapter<MainAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return PostViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (topList.isNullOrEmpty()) return 0
        return topList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val onePost = topList[position]

    }

    fun addData(listItems: List<DataModel>) {
        val size = topList.size
        topList.addAll(listItems.subtract(topList))
        val sizeNew = topList.size
        notifyItemRangeChanged(size, sizeNew)
    }

    class PostViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvAuthor: TextView
        var tvDate: TextView
        var tvPost: TextView
        var ivPost: ImageView
        var tvLink: TextView
        var tvRating: TextView
        var tvComments: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvDate = itemView.findViewById(R.id.tvDate)
            tvPost = itemView.findViewById(R.id.tvPost)
            ivPost = itemView.findViewById(R.id.ivPost) as ImageView
            tvLink = itemView.findViewById(R.id.tvLink)
            tvRating = itemView.findViewById(R.id.tvRating)
            tvComments = itemView.findViewById(R.id.tvComments)
        }

    }
}