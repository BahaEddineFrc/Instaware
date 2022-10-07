package com.enablon.instaware.data.remote.instagram

import com.enablon.instaware.data.remote.*
import com.enablon.instaware.domain.model.media.MediaListResponse
import com.enablon.instaware.domain.model.media.MediaPost
import com.enablon.instaware.domain.model.user.UserInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Instagram API services
 */
interface InstagramServices {

    @GET(InstagramEndPoints.USER_INFO)
    fun getUserInfo(
        @Query(FIELDS_KEY) fields: String = USER_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<UserInfo>>

    @GET(InstagramEndPoints.MEDIA_LIST)
    fun getMediaList(
        @Query(LIMIT_KEY) limit: Int,
        @Query(AFTER_KEY) after: String?,
        @Query(FIELDS_KEY) fields: String = MEDIA_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<MediaListResponse>>

    @GET(InstagramEndPoints.MEDIA_BY_ID)
    fun getMediaById(
        @Path(MEDIA_ID_KEY) mediaId: String,
        @Query(FIELDS_KEY) fields: String = MEDIA_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<MediaPost>>
}