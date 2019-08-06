package com.aungthuya.codetest.util

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

/**
 * Image loading function to extend to ImageView.
 *
 * @param url Image url over the network.
 * @param placeholder Placeholder image if the request image is fail.
 * @param isCircle Change the image to circular shape.
 */
fun ImageView.load(url: String?, @DrawableRes placeholder: Int? = null, isCircle: Boolean = false) {
    when (url) {
        null -> placeholder?.let { setImageResource(it) }
        else -> {
            val requestOptions = RequestOptions()
            placeholder?.let { requestOptions.placeholder(it) }

            if (isCircle)
                requestOptions.circleCrop()

            Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(this)
        }
    }
}
