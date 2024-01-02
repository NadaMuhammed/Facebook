package com.example.facebook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class PostActivity : AppCompatActivity() {
    lateinit var createPostBtn: Button
    val posts = StaticArrayList.get()
    var post: Post? = null
    lateinit var postsAdapter: PostsAdapter
    lateinit var postsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE
        createPostBtn = findViewById(R.id.createPostBtn)
        postsAdapter = PostsAdapter(posts)
        postsRecyclerView = findViewById(R.id.postsRecyclerView)
        postsRecyclerView.adapter = postsAdapter
        postsAdapter.onPostClick = object : PostsAdapter.OnPostClick {
            override fun onPostClick(post: Post, position: Int) {
                val intent = Intent(this@PostActivity, PostDetailsActivity::class.java)
                intent.putExtra("post", posts[position])
                startActivity(intent)
            }
        }
        getIntentPost()
        createPostBtn.setOnClickListener {
            val intent = Intent(this, CreateNewPostActivity::class.java)
            startActivity(intent)

        }
    }

    private fun getIntentPost() {
        post = intent?.getParcelableExtra<Post>("post")
        if (post != null) {
            progressBar.visibility = View.VISIBLE
            Handler().postDelayed({
                post = Post(post!!.caption, post!!.image)
                posts.add(0, post!!)
                postsAdapter.notifyDataSetChanged()
                progressBar.visibility = View.INVISIBLE
            }, 2000)
        }
    }
}