package com.enablon.instaware.domain.repository

import com.enablon.instaware.domain.model.media.MediaListResponse
import com.enablon.instaware.domain.model.media.MediaPost
import com.enablon.instaware.domain.model.user.UserInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

/**
 * An interface containing the media API requesting methods
 */
interface MediaRepository {
    suspend fun getUserInfo(): Single<Response<UserInfo>>
    suspend fun getMediaList(limit: Int, after: String?): Single<Response<MediaListResponse>>
    suspend fun getMediaById(mediaId: String): Single<Response<MediaPost>>
}