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
import com.example.facebook.databinding.ActivityCreateNewPostBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CreateNewPostActivity : AppCompatActivity() {
    lateinit var postCaptionEd: EditText
    lateinit var addPhotoBtn: Button
    lateinit var postImv: ImageView
    lateinit var postBtn: Button
    lateinit var binding: ActivityCreateNewPostBinding
    var uri: Uri = Uri.EMPTY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postCaptionEd = binding.newPostCaptionEd
        addPhotoBtn = binding.addPhotosBtn
        postImv = binding.newPostImv
        postBtn = binding.postBtn
        postCaptionTextChangedListener()
        addPhotoBtn.setOnClickListener {
            openGallery()
        }
        postBtn.setOnClickListener {
            if (postCaptionEd.text.isNotEmpty() || uri != Uri.EMPTY) {
                val post = Post(
                    User(Constants.USERNAME_UNBLAST, R.drawable.colorcard),
                    postCaptionEd.text.toString(),
                    uri,
                    getDate()
                )
                val intent = Intent(this, PostActivity::class.java)
                intent.putExtra("post", post)
                startActivity(intent)
            }
        }
    }

    private fun getDate(): String {
        val time = Calendar.getInstance().time
        return time.toString("dd MMM yyyy") + " at " + time.toString("hh:mm")
    }

    private fun openGallery() {
        val intent = Intent()
        intent.setType("image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10)
    }

    private fun postCaptionTextChangedListener() {
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

    fun Date.toString(format: String): String {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(this)
    }
}