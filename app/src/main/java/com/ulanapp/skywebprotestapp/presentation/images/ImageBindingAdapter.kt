package com.ulanapp.skywebprotestapp.presentation.images

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageBindingAdapter {

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.setImage(url: String) {
            Glide.with(context).load(url).into(this)
        }
    }
}