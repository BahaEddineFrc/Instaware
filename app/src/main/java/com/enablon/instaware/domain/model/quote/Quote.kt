package com.enablon.instaware.domain.model.quote

import com.google.gson.annotations.SerializedName

/**
 * A Quote response entity
 */
data class Quote(
    @SerializedName("_id")
    val id: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("author")
    val author: String
)