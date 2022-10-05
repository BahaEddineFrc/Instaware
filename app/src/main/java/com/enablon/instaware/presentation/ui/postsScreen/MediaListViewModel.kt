package com.enablon.instaware.presentation.ui.postsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enablon.instaware.common.UNKNOWN_ERROR_MSG
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.common.utils.logi
import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.model.MediaPost
import com.enablon.instaware.domain.usecase.GetMediaListUseCase
import com.enablon.instaware.common.AppResult
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MediaListViewModel : ViewModel(), KoinComponent {

    private val getMediaListUseCase: GetMediaListUseCase by inject()

    private val _mediaPosts = MutableLiveData<List<MediaPost>>()
    val mediaPosts: LiveData<List<MediaPost>>
        get() = _mediaPosts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getMediaList() {
        viewModelScope.launch {
            getMediaListUseCase()
                .subscribe(
                    { result ->
                        if (result is AppResult.Success) {
                            logi { "Server request failed getMediaListUseCase > ${result.data}" }
                            val res = result.data as MediaListResponse
                            _mediaPosts.postValue(res.data)
                        } else {
                            loge { "Server request failed getMediaListUseCase > ${result.message}" }
                            _error.postValue(result.message ?: UNKNOWN_ERROR_MSG)
                        }
                    }, {
                        loge { "Server request failed getMediaListUseCase > $it" }
                        _error.postValue(it.localizedMessage ?: UNKNOWN_ERROR_MSG)
                    }
                )
        }
    }
}