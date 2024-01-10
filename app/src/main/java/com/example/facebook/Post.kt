package com.example.facebook

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post (var user: User, var caption: String, var image: Uri, var timePosted: String) : Parcelable