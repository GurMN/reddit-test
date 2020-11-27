package com.test.redditproject.ui.main

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.redditproject.R
import com.test.redditproject.api.DataModel
import com.test.redditproject.util.makeClickableSpan
import java.util.concurrent.TimeUnit

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
        val ctx = holder.itemView.context
        with(onePost) {
            holder.tvTitle.text = title
            holder.tvAuthor.text = author
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(created_utc)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(created_utc)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(created_utc)
            val days: Long = TimeUnit.MILLISECONDS.toDays(created_utc)
            val sb = StringBuilder()
            when {
                seconds < 60 -> sb.append(ctx.getString(R.string.time_seconds_ago, seconds))
                minutes < 60 -> sb.append(ctx.getString(R.string.time_minutes_ago, minutes))
                hours < 24 -> sb.append(ctx.getString(R.string.time_hours_ago, hours))
                else -> sb.append(ctx.getString(R.string.time_days_ago, days))
            }
            holder.tvDate.text = sb.toString()
            holder.tvPost.text = selftext
            if (!thumbnail.isNullOrEmpty()) {
                holder.ivPost.isVisible = true
                Glide.with(holder.ivPost)
                    .load(thumbnail)
                    .dontAnimate()
                    .into(holder.ivPost)
            }
            val spannableString = SpannableString(url)
            spannableString.setSpan(
                url?.makeClickableSpan(),
                0,
                spannableString.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            holder.tvLink.text = spannableString
            holder.tvLink.movementMethod = LinkMovementMethod.getInstance()
            holder.tvRating.text = score.toString()
            holder.tvComments.text = this.num_comments.toString()
        }
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