package com.enablon.instaware.data.remote

import com.enablon.instaware.domain.model.Media
import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.model.UserInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface InstagramServices {

    @GET(InstagramEndPoints.USER_INFO)
    fun getUserInfo(
        @Path("fields") fields: String = "id,media_count,account_type,username",
        @Path("token") token: String = "IGQVJXUGhZAYmc1Y080OFNaZAndQOTVabzVrdkxpZADg2TWNGeVlQLTFTSzF3VjdWN2hFUVNCaTBPVzExZAHJsNElBNFJtT3d4TlExWVYxZAXVDLVlTQ2JNQ1lMN3BXZATZAFMVJERTZAPNkNCNno5QjUyTnY2dQZDZD"
    ): Observable<UserInfo>

    @GET(InstagramEndPoints.MEDIA_BY_ID)
    fun getMediaById(
        @Path("mediaId") mediaId: String = "",
        @Path("fields") fields: String = "id,caption,media_type,media_url,thumbnail_url,username,timestamp",
        @Path("token") token: String = "IGQVJXUGhZAYmc1Y080OFNaZAndQOTVabzVrdkxpZADg2TWNGeVlQLTFTSzF3VjdWN2hFUVNCaTBPVzExZAHJsNElBNFJtT3d4TlExWVYxZAXVDLVlTQ2JNQ1lMN3BXZATZAFMVJERTZAPNkNCNno5QjUyTnY2dQZDZD"
    ): Observable<Media>

    @GET(InstagramEndPoints.MEDIA_LIST)
    fun getMediaList(
        @Path("apiVersion") apiVersion: String = "v15.0",
        @Path("userId") userId: String = "",
        @Path("token") token: String = "IGQVJXUGhZAYmc1Y080OFNaZAndQOTVabzVrdkxpZADg2TWNGeVlQLTFTSzF3VjdWN2hFUVNCaTBPVzExZAHJsNElBNFJtT3d4TlExWVYxZAXVDLVlTQ2JNQ1lMN3BXZATZAFMVJERTZAPNkNCNno5QjUyTnY2dQZDZD"
    ): Observable<MediaListResponse>


    @GET(InstagramEndPoints.MEDIA_BY_ID_CHILDREN)
    fun getMediaByIdChildren(
        @Path("mediaId") mediaId: String = "",
        @Path("token") token: String = "IGQVJXUGhZAYmc1Y080OFNaZAndQOTVabzVrdkxpZADg2TWNGeVlQLTFTSzF3VjdWN2hFUVNCaTBPVzExZAHJsNElBNFJtT3d4TlExWVYxZAXVDLVlTQ2JNQ1lMN3BXZATZAFMVJERTZAPNkNCNno5QjUyTnY2dQZDZD"
    ): Observable<MediaListResponse>
}