package com.hacybeyker.movieoh.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("srcImage")
fun srcImage(imageView: AppCompatImageView, url: String?) {
    url?.let {
        imageView.loadImage(it)
    }
}