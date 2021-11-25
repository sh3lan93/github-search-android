package com.shalan.searchgithub.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("app:load")
fun loadImage(view: ImageView, url: String?){
    view.load(url)
}