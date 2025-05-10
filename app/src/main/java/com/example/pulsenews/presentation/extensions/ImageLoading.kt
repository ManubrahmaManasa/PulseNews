package com.example.pulsenews.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.pulsenews.R

fun ImageView.loadImage(imageUrl:String) {
    Glide.with(this.context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}