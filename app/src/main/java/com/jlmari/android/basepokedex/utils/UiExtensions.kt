package com.jlmari.android.basepokedex.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jlmari.android.basepokedex.R

/**
 * Load model into ImageView with center crop transformation using Glide.
 *
 * @param model - Any object supported by Glide (Uri, File, Bitmap, String, Resource, ByteArray, and Drawable)
 *
 */
fun <T> ImageView.loadImage(model: T) {
    Glide.with(context)
        .asBitmap()
        .load(model)
        .placeholder(R.mipmap.ic_launcher)
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}
