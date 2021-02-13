package com.app.instagramclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MainRecyclerAdapter(var feedList: ArrayList<MainResponse>, var mContext: Context) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dpImageView: ShapeableImageView = itemView.findViewById(R.id.user_dp_view)
        val titleView: TextView = itemView.findViewById(R.id.title_view)
        val subTitleView: TextView = itemView.findViewById(R.id.subtitle_view)
        val postedImageView: ImageView = itemView.findViewById(R.id.posted_image_view)
        val postedText: TextView = itemView.findViewById(R.id.post_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_child, parent, false)
        return MainViewHolder(view);
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.subTitleView.text = feedList[position].title
        holder.titleView.text = feedList[position].title
        holder.postedText.text = feedList[position].title
//        Glide.with(mContext).load(feedList[position].thumbnailUrl).into(holder.dpImageView)
//        Glide.with(mContext).load(feedList[position].url).into(holder.postedImageView)
        Picasso.get().load(feedList[position].thumbnailUrl).into(holder.dpImageView)
        Picasso.get().load(feedList[position].url).into(holder.postedImageView)
    }
}