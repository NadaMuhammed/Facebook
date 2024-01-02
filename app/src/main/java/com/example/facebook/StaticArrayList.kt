package com.example.facebook

class StaticArrayList {
    companion object{
        val postsArrayList = ArrayList<Post>()

        fun get(): ArrayList<Post>{
            return postsArrayList
        }
    }
}