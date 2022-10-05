package com.enablon.instaware.domain.usecase

import com.enablon.instaware.common.AppResult
import com.enablon.instaware.data.utils.parseServerResponse
import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.repository.MediaRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent

class GetMediaListUseCase(private val postsRepository: MediaRepository) : KoinComponent {

    suspend operator fun invoke(): Single<AppResult<MediaListResponse?>> =
        postsRepository.getMediaList()
            .map { it.parseServerResponse() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}