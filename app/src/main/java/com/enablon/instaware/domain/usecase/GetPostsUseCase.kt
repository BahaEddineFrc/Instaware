package com.enablon.instaware.domain.usecase

import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.repository.MediaRepository
import com.enablon.instaware.common.AppResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject

class GetMediaListUseCase(private val postsRepository: MediaRepository) {
    suspend operator fun invoke(): Single<AppResult<MediaListResponse?>> =
        postsRepository.getMediaList()
            .map {
                if (it.isSuccessful) {
                    loge { "GetMediaList succeeded > ${it.body()}" }
                    AppResult.Success(it.body())
                } else {
                    try {
                        val jsonError = JSONObject(it.errorBody()!!.string())
                        loge { "GetMediaList failed > $jsonError" }
                        AppResult.Error(jsonError.toString())
                    } catch (e: Exception) {
                        loge { "GetMediaList failed > $e" }
                        AppResult.Error(e.toString())
                    }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}