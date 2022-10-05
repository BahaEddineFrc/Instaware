package com.enablon.instaware.data.repository

import com.enablon.instaware.data.remote.InstagramServices
import com.enablon.instaware.domain.repository.MediaRepository

class MediaRepositoryImpl(private val services: InstagramServices) : MediaRepository {

    override suspend fun getUserInfo() = services.getUserInfo()

    override suspend fun getMediaList() = services.getMediaList()

    override suspend fun getMediaById(mediaId: String) = services.getMediaById(mediaId)

    override suspend fun getMediaByIdChildren(mediaId: String) =
        services.getMediaByIdChildren(mediaId)
}