package com.enablon.instaware.domain.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class MediaPost(
    @SerializedName("id")
    val id: String,
    @SerializedName("media_type")
    val mediaType: String?, //CAROUSEL_ALBUM
    @SerializedName("media_url")
    val mediaUrl: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("timestamp")
    val timestamp: String? // "2020-07-15T17:56:39+0000"
) {
    class DiffCallback : DiffUtil.ItemCallback<MediaPost>() {
        override fun areItemsTheSame(
            oldItem: MediaPost,
            newItem: MediaPost
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MediaPost,
            newItem: MediaPost
        ): Boolean {
            return oldItem.timestamp == newItem.timestamp
                    && oldItem.mediaType == newItem.mediaType
                    && oldItem.mediaUrl == newItem.mediaUrl
                    && oldItem.caption == newItem.caption
        }
    }
}