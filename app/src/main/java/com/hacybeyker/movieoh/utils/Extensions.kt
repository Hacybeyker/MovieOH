package com.hacybeyker.movieoh.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hacybeyker.movieoh.R

fun ImageView.loadImage(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this.context)
            .asBitmap()
            .load("https://image.tmdb.org/t/p/w500$url")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(this)
    }
}