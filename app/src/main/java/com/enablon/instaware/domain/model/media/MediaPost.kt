package com.enablon.instaware.domain.model.media

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

/**
 * An Instagram Media entity
 * used by [MediaListResponse]
 */
data class MediaPost(
    @SerializedName("id")
    val id: String,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("media_url")
    val mediaUrl: String?,
    @SerializedName("children")
    val children: MediaListResponse?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("timestamp")
    val timestamp: String?
) {
    /**
     * A class representing how to differ instances of [MediaPost]
     * used by [com.enablon.instaware.presentation.ui.postsScreen.list.MediaListAdapter] to know when to update list items
     */
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