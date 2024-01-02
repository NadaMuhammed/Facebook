package com.example.facebook

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post (var caption: String, var image: Uri) : Parcelable