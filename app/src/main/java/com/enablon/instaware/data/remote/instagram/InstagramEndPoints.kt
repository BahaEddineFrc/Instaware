package com.enablon.instaware.data.remote.instagram

import com.enablon.instaware.data.remote.BASE_URL

object InstagramEndPoints {
    const val USER_INFO = "${BASE_URL}me"
    const val MEDIA_BY_ID = "$BASE_URL{mediaId}"
    const val MEDIA_LIST = "${BASE_URL}me/media"
    const val MEDIA_BY_ID_CHILDREN = "$BASE_URL{mediaId}/children"
}