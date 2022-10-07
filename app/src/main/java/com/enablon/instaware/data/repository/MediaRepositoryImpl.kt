package com.enablon.instaware.data.repository

import com.enablon.instaware.data.remote.instagram.InstagramServices
import com.enablon.instaware.domain.repository.MediaRepository
import org.koin.core.component.KoinComponent

/**
 * The implementation of the [MediaRepository] methods
 */
class MediaRepositoryImpl(private val services: InstagramServices) : MediaRepository,
    KoinComponent {

    /**
     * Request the current user's info
     */
    override suspend fun getUserInfo() = services.getUserInfo()

    /**
     * Request the current user's Media list
     */
    override suspend fun getMediaList(limit: Int, after: String?) =
        services.getMediaList(limit, after)

    /**
     * Request media details by id
     */
    override suspend fun getMediaById(mediaId: String) = services.getMediaById(mediaId)
}