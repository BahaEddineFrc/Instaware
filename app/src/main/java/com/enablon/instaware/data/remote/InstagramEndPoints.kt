package com.enablon.instaware.data.remote

import com.enablon.instaware.data.remote.NetworkClient.Companion.BASE_URL

object InstagramEndPoints {
    const val USER_INFO = "${BASE_URL}me?fields={fields}&access_token={token}"
    const val MEDIA_BY_ID = "$BASE_URL{mediaId}?fields={fields}&access_token={token}"
    const val MEDIA_LIST = "$BASE_URL{apiVersion}/{userId}/media?access_token={access-token}"
    const val MEDIA_BY_ID_CHILDREN = "$BASE_URL{mediaId}/children?access_token={token}"
}