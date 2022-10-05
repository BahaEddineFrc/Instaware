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
import com.enablon.instaware.common.CONNECTION_ERROR_MSG
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

    init {
        getMediaList()
    }

    fun getMediaList() {
        viewModelScope.launch {
            getMediaListUseCase()
                .subscribe(
                    { result ->
                        if (result is AppResult.Success) {
                            val res = result.data as MediaListResponse
                            _mediaPosts.postValue(res.data)
                        } else {
                            loge { "MediaListViewModel - getMediaListUseCase failed - reason : ${result.message}" }
                            _error.postValue(result.message ?: UNKNOWN_ERROR_MSG)
                        }
                    }, {
                        loge { "MediaListViewModel - getMediaListUseCase server request failed - exception : $it" }
                        _error.postValue(CONNECTION_ERROR_MSG)
                    }
                )
        }
    }
}