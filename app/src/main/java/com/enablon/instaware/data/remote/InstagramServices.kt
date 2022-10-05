package com.enablon.instaware.data.remote

import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.model.MediaPost
import com.enablon.instaware.domain.model.UserInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InstagramServices {

    @GET(InstagramEndPoints.USER_INFO)
    fun getUserInfo(
        @Query(FIELDS_KEY) fields: String = USER_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<UserInfo>>

    @GET(InstagramEndPoints.MEDIA_BY_ID)
    fun getMediaById(
        @Path(MEDIA_ID_KEY) mediaId: String,
        @Query(FIELDS_KEY) fields: String = MEDIA_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<MediaPost>>

    @GET(InstagramEndPoints.MEDIA_LIST)
    fun getMediaList(
        @Query(FIELDS_KEY) fields: String = MEDIA_REQUEST_FIELDS,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<MediaListResponse>>


    @GET(InstagramEndPoints.MEDIA_BY_ID_CHILDREN)
    fun getMediaByIdChildren(
        @Path(FIELDS_KEY) mediaId: String,
        @Query(TOKEN_KEY) token: String = TOKEN
    ): Single<Response<MediaListResponse>>
}