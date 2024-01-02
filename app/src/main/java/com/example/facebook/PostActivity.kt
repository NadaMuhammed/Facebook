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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView

class PostActivity : AppCompatActivity() {
    lateinit var createPostBtn: Button
    lateinit var alertDialog: AlertDialog
    lateinit var postCaptionEd: EditText
    lateinit var addPhotoBtn: Button
    lateinit var postImv: ImageView
    var uri: Uri = Uri.EMPTY
    lateinit var postBtn: Button
    val posts = ArrayList<Post>()
    lateinit var post: Post
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
        createPostBtn.setOnClickListener {
            initPostDialog()
            alertDialog.show()

            addPhotoBtn.setOnClickListener { view ->
                openGallery()
            }

            postCaptionTextChangedListener()

            postBtn.setOnClickListener {
                alertDialog.dismiss()
                progressBar.visibility = View.VISIBLE
                if (postCaptionEd.text.isNotEmpty() || uri != Uri.EMPTY) {
                    Handler().postDelayed({
                        post = Post(postCaptionEd.text.toString(), uri)
                        posts.add(0, post)
                        postsAdapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
                    }, 2000)

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            when (requestCode) {
                10 -> if (resultCode == Activity.RESULT_OK) {
                    postImv.setImageURI(data?.data)
                    uri = data?.data!!
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.e("ImageCancelled", "Selecting Image Cancelled")
                }

            }
        } catch (e: Exception) {
            Log.e("ImageCancelled", e.message.toString())

        }
    }

    private fun initPostDialog() {
        val builder = AlertDialog.Builder(this)
        val postView = layoutInflater.inflate(R.layout.create_new_post, null)
        initPostDialogViews(postView)
        builder.setView(postView)
        alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(true)
    }

    private fun initPostDialogViews(postView: View) {
        postCaptionEd = postView.findViewById(R.id.newPostCaptionEd)
        addPhotoBtn = postView.findViewById(R.id.addPhotosBtn)
        postImv = postView.findViewById(R.id.newPostImv)
        postBtn = postView.findViewById(R.id.postBtn)
    }

    private fun openGallery(){
        val intent = Intent()
        intent.setType("image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10)
    }

    private fun postCaptionTextChangedListener(){
        postCaptionEd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                postBtn.isClickable = true
                postBtn.alpha = 1f
            }

            override fun afterTextChanged(p0: Editable?) {
                postBtn.isClickable = true
                postBtn.alpha = 1f
            }

        })
    }
}