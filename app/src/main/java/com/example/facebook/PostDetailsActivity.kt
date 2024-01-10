package com.example.facebook

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.facebook.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {
    lateinit var postCaption: TextView
    lateinit var postImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_item)
        postCaption = findViewById(R.id.postCaptionTv)
        postImageView = findViewById(R.id.postImv)
        val post = intent.getParcelableExtra<Post>("post")
        if (post?.image?.equals(Uri.EMPTY) == true) {
            postImageView.visibility = View.GONE
        } else {
            postImageView.setImageURI(post?.image)
        }
        if (post?.caption.isNullOrEmpty()) {
            postCaption.visibility = View.GONE
        } else {
            postCaption.text = post?.caption
        }
    }
}