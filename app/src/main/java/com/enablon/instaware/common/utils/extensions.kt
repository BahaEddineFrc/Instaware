package com.enablon.instaware.common.utils

import android.util.Log
import android.widget.ImageView

import com.enablon.instaware.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?) {
    if (url != null)
        Picasso.get().load(url)
            .error(R.drawable.ic_no_data)
            .placeholder(R.drawable.loader_image_placeholder)
            .into(this)
    else this.setImageResource(R.drawable.ic_no_data)
}