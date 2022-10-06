package com.enablon.instaware.presentation.ui.postsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enablon.instaware.common.AppResult
import com.enablon.instaware.common.CONNECTION_ERROR_MSG
import com.enablon.instaware.common.UNKNOWN_ERROR_MSG
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.domain.model.MediaListResponse
import com.enablon.instaware.domain.model.MediaPost
import com.enablon.instaware.domain.usecase.GetMediaListUseCase
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

    private var paginationIndicator: String? = null

    init {
        getMediaList()
    }

    fun getMediaList() {
        if (paginationIndicator != null || _mediaPosts.value == null)
            viewModelScope.launch {
                loge { "MediaListViewModel - USING AS PARAM $paginationIndicator" }
                getMediaListUseCase(paginationIndicator)
                    .subscribe(
                        { result ->
                            if (result is AppResult.Success) {
                                val res = result.data as MediaListResponse
                                _mediaPosts.postValue(_mediaPosts.value?.plus(res.data) ?: res.data)
                                paginationIndicator = if(res.paging.next==null) null else res.paging.cursors.after
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