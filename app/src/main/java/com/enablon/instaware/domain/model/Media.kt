package com.enablon.instaware.domain.model

import com.google.gson.annotations.SerializedName

data class Media(
    val id: String,
    @SerializedName("media_type")
    val mediaType: String?, //CAROUSEL_ALBUM
    @SerializedName("media_url")
    val mediaUrl: String?,
    val username: String?,
    val caption: String?,
    val timestamp: String? // "2020-07-15T17:56:39+0000"
)