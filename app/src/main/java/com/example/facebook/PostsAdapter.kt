package com.example.facebook

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(var posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    lateinit var onPostClick: OnPostClick
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postCaption = itemView.findViewById<TextView>(R.id.postCaption)
        var postImv = itemView.findViewById<ImageView>(R.id.postImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if (posts[position].caption.isEmpty()) {
            holder.postCaption.visibility = View.GONE
        } else {
            holder.postCaption.text = posts[position].caption
        }
        if (posts[position].image.equals(Uri.EMPTY)) {
            holder.postImv.visibility = View.GONE
        } else {
            holder.postImv.visibility = View.VISIBLE
            holder.postImv.setImageURI(posts[position].image)
        }
        holder.itemView.setOnClickListener {
            onPostClick.onPostClick(posts[position],position)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    interface OnPostClick{
        fun onPostClick(post: Post, position: Int)
    }
}