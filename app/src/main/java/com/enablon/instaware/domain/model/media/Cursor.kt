package com.enablon.instaware.domain.model.media

import com.google.gson.annotations.SerializedName

/**
 * The start and end cursors of the instagram request
 * used by [Paging]
 */
data class Cursor(
    @SerializedName("before")
    val before: String,
    @SerializedName("after")
    val after: String
)
