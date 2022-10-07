package com.enablon.instaware.data.repository

import com.enablon.instaware.data.remote.instagram.InstagramServices
import com.enablon.instaware.domain.repository.MediaRepository
import org.koin.core.component.KoinComponent

class MediaRepositoryImpl(private val services: InstagramServices) : MediaRepository,
    KoinComponent {

    override suspend fun getUserInfo() = services.getUserInfo()

    override suspend fun getMediaList(limit: Int, after: String?) =
        services.getMediaList(limit, after)

    override suspend fun getMediaById(mediaId: String) = services.getMediaById(mediaId)

    override suspend fun getMediaByIdChildren(mediaId: String) =
        services.getMediaByIdChildren(mediaId)
}