package com.example.facebook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    lateinit var createPostBtn: Button
    val posts = StaticArrayList.get()
    var post: Post? = null
    lateinit var postsAdapter: PostsAdapter
    lateinit var postsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = binding.progressBar
        progressBar.visibility = View.INVISIBLE
        createPostBtn = binding.createPostBtn
        postsAdapter = PostsAdapter(posts)
        postsRecyclerView = binding.postsRecyclerView
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
                post = Post(post!!.user, post!!.caption, post!!.image, post!!.timePosted)
                posts.add(0, post!!)
                postsAdapter.notifyDataSetChanged()
                progressBar.visibility = View.INVISIBLE
            }, 2000)
        }
    }

}