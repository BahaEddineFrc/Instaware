package com.enablon.instaware.data.remote.instagram

import com.enablon.instaware.data.remote.INSTAGRAM_BASE_URL

/**
 * End points of the instagram API
 */
object InstagramEndPoints {
    const val USER_INFO = "${INSTAGRAM_BASE_URL}me"
    const val MEDIA_BY_ID = "$INSTAGRAM_BASE_URL{mediaId}"
    const val MEDIA_LIST = "${INSTAGRAM_BASE_URL}me/media"
}