package com.enablon.instaware.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id: String,
    @SerializedName("media_count")
    val mediaCount: Int?,
    @SerializedName("account_type")
    val accountType: String?,
    val username: String?
)