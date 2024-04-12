package com.example.filmimdbappkotlin.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.example.filmimdbappkotlin.R

fun ImageView.loadCircleImage(path : String) {

    Glide.with(this.context)
        .load(Constants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform().error(R.drawable.baseline_error_24)
            .circleCrop())
        .into(this)
}

fun ImageView.loadImage(path : String) {

    Glide.with(this.context)
        .load(Constants.IMAGE_BASE_URL + path)
        .apply(centerCropTransform().error(R.drawable.baseline_error_24))
        .into(this)
}