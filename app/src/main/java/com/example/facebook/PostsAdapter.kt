package com.example.facebook

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(var postsList: ArrayList<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    lateinit var onPostClick: OnPostClick
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postCaption = itemView.findViewById<TextView>(R.id.postCaptionTv)
        var postImv = itemView.findViewById<ImageView>(R.id.postImv)
        var userName = itemView.findViewById<TextView>(R.id.usernameTv)
        var userProfilePicture = itemView.findViewById<ImageView>(R.id.postItemUserProfileImv)
        var timePosted = itemView.findViewById<TextView>(R.id.timePostedTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if (postsList[position].caption.isEmpty()) {
            holder.postCaption.visibility = View.GONE
        } else {
            holder.postCaption.text = postsList[position].caption
        }
        if (postsList[position].image.equals(Uri.EMPTY)) {
            holder.postImv.visibility = View.GONE
        } else {
            holder.postImv.visibility = View.VISIBLE
            holder.postImv.setImageURI(postsList[position].image)
        }

        holder.userName.text = postsList[position].user.username
        holder.userProfilePicture.setImageResource(postsList[position].user.profilePicture)
        holder.timePosted.text = postsList[position].timePosted

        holder.itemView.setOnClickListener {
            onPostClick.onPostClick(postsList[position],position)
        }
    }

    override fun getItemCount(): Int = postsList.size

    interface OnPostClick{
        fun onPostClick(post: Post, position: Int)
    }
}