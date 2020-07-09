package com.lukienko.androidappskeleton.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lukienko.androidappskeleton.R

class ImageLoader {

    fun loadCircleAvatar(
        imageView: ImageView,
        imageUrl: String
    ) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(RequestOptions().centerCrop().circleCrop())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}
