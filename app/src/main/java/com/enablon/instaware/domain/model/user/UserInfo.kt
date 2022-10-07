package com.enablon.instaware.domain.model.user

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")
    val id: String,
    @SerializedName("media_count")
    val mediaCount: Int?,
    @SerializedName("account_type")
    val accountType: String?,
    @SerializedName("username")
    val username: String?
)