package com.enablon.instaware.common.utils

import android.widget.ImageView
import com.enablon.instaware.R
import com.squareup.picasso.Picasso

/**
 * Load an image by url into an ImageView
 */
fun ImageView.loadUrl(url: String?) {
    if (url != null)
        Picasso.get().load(url)
            .error(R.drawable.ic_no_data)
            .placeholder(R.drawable.loader_image_placeholder)
            .into(this)
    else this.setImageResource(R.drawable.ic_no_data)
}
