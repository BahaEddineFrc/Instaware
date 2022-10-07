package com.enablon.instaware.presentation.ui.postsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enablon.instaware.common.AppResult
import com.enablon.instaware.common.CONNECTION_ERROR_MSG
import com.enablon.instaware.common.QUOTE_API_CALL_DELAY
import com.enablon.instaware.common.UNKNOWN_ERROR_MSG
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.common.utils.logi
import com.enablon.instaware.domain.model.media.MediaListResponse
import com.enablon.instaware.domain.model.media.MediaPost
import com.enablon.instaware.domain.model.quote.Quote
import com.enablon.instaware.domain.usecase.GetMediaListUseCase
import com.enablon.instaware.domain.usecase.GetNewQuoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MediaListViewModel : ViewModel(), KoinComponent {

    private val getMediaListUseCase: GetMediaListUseCase by inject()
    private val getNewQuoteUseCase: GetNewQuoteUseCase by inject()

    private val _mediaPosts = MutableLiveData<List<MediaPost>>()
    val mediaPosts: LiveData<List<MediaPost>>
        get() = _mediaPosts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _newQuote = MutableLiveData<Quote>()
    val newQuote: LiveData<Quote>
        get() = _newQuote

    private var paginationIndicator: String? = null

    init {
        getMediaList()
        getNewQuote()
    }

    fun getMediaList() {
        if (paginationIndicator != null || _mediaPosts.value == null)
            viewModelScope.launch {
                getMediaListUseCase(paginationIndicator)
                    .subscribe(
                        { result ->
                            if (result is AppResult.Success) {
                                val res = result.data as MediaListResponse
                                _mediaPosts.postValue(_mediaPosts.value?.plus(res.data) ?: res.data)
                                paginationIndicator =
                                    if (res.paging.next == null) null else res.paging.cursors.after
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


    fun getNewQuote() {
            viewModelScope.launch(Dispatchers.IO) {
                while(isActive) {
                    getNewQuoteUseCase()
                        .subscribe(
                            { result ->
                                if (result.text.isNotEmpty()) {
                                    logi { "MediaListViewModel - getNewQuoteUseCase quote success : $result" }
                                    _newQuote.postValue(result)
                                }
                            }, {
                                loge { "MediaListViewModel - getNewQuoteUseCase server request failed - exception : $it" }
                            }
                        )
                    delay(QUOTE_API_CALL_DELAY)
                }
            }
    }
}