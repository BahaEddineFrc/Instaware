package com.enablon.instaware.domain.model.media

import com.google.gson.annotations.SerializedName

data class MediaListResponse(
    @SerializedName("data")
    val data : List<MediaPost>,
    @SerializedName("paging")
    val paging: Paging
)