package com.example.facebook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (val username:String, val profilePicture: Int) : Parcelable