package com.example.facebook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class CreateNewPostActivity : AppCompatActivity() {
    lateinit var postCaptionEd: EditText
    lateinit var addPhotoBtn: Button
    lateinit var postImv: ImageView
    lateinit var postBtn: Button
    var uri: Uri = Uri.EMPTY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)
        postCaptionEd = findViewById(R.id.newPostCaptionEd)
        addPhotoBtn = findViewById(R.id.addPhotosBtn)
        postImv = findViewById(R.id.newPostImv)
        postBtn = findViewById(R.id.postBtn)
        postCaptionTextChangedListener()
        addPhotoBtn.setOnClickListener {
            openGallery()
        }
        postBtn.setOnClickListener {
            if (postCaptionEd.text.isNotEmpty() || uri != Uri.EMPTY) {
                val post = Post(postCaptionEd.text.toString(), uri)
                val intent = Intent(this, PostActivity::class.java)
                intent.putExtra("post",post)
                startActivity(intent)
            }
        }
    }
    private fun openGallery(){
        val intent = Intent()
        intent.setType("image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10)
    }
    private fun postCaptionTextChangedListener(){
        postCaptionEd.addTextChangedListener(object : TextWatcher {
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            when (requestCode) {
                10 -> if (resultCode == Activity.RESULT_OK) {
                    postImv.setImageURI(data?.data)
                    uri = data?.data!!
                    postBtn.isClickable = true
                    postBtn.alpha = 1f
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.e("ImageCancelled", "Selecting Image Cancelled")
                }

            }
        } catch (e: Exception) {
            Log.e("ImageCancelled", e.message.toString())

        }
    }
}