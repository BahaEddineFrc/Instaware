package com.enablon.instaware.data.remote

object InstagramEndPoints {
    const val USER_INFO = "${BASE_URL}me?fields={fields}&access_token={token}"
    const val MEDIA_BY_ID = "$BASE_URL{mediaId}?fields={fields}&access_token={token}"
    const val MEDIA_LIST = "${BASE_URL}me/media?fields={fields}&access_token={access-token}"
    const val MEDIA_BY_ID_CHILDREN = "$BASE_URL{mediaId}/children?access_token={token}"
    ///me/media?fields={fields}&access_token={access-token}
    // &limit={number-of-media-you-want-to-return}
    // &next={next-endpoint}
}