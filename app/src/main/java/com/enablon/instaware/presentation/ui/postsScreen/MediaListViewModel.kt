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
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MediaListViewModel : ViewModel(), KoinComponent {

    /**
     * Use cases
     */
    private val getMediaListUseCase: GetMediaListUseCase by inject()
    private val getNewQuoteUseCase: GetNewQuoteUseCase by inject()

    /**
     * An observable containing the media posts list
     * Notifies observers when its value changes
     */
    private val _mediaPosts = MutableLiveData<List<MediaPost>>()
    val mediaPosts: LiveData<List<MediaPost>>
        get() = _mediaPosts

    /**
     * An observable containing the mediaList error message
     * Notifies observers when its value changes
     */
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    /**
     * An observable containing the received random quote
     * Notifies observers when its value changes
     */
    private val _newQuote = MutableLiveData<Quote>()
    val newQuote: LiveData<Quote>
        get() = _newQuote

    /**
     * An observable representing a timer to be used to display an AlertDialog
     * Notifies observers when its value changes
     */
    private val _timeUsageEnded = MutableLiveData<Boolean>(false)
    val timeUsageEnded: LiveData<Boolean>
        get() = _timeUsageEnded

    /**
     * The pagination cursor used and updated by the mediaList query
     * to display more items
     */
    private var paginationIndicator: String? = null

    /**
     * Launch requests at viewModel initialization
     */
    init {
        getMediaList()
        getNewQuote()
        setTimer()
    }

    /**
     * Request the current user's list of Instagram media posts
     */
    fun getMediaList() {
        if (paginationIndicator != null || _mediaPosts.value == null)
            viewModelScope.launch {
                getMediaListUseCase(paginationIndicator)
                    .subscribe(
                        { result ->
                            if (result is AppResult.Success) {
                                val res = result.data as MediaListResponse
                                // update the media list observable
                                _mediaPosts.postValue(_mediaPosts.value?.plus(res.data) ?: res.data)
                                // update the pagination cursor
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

    /**
     * Request a new random quote evey [QUOTE_API_CALL_DELAY] period of time
     */
    fun getNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
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

    /**
     * Set a timer to update [timeUsageEnded]
     */
    private var timer: Int = 50
    private fun setTimer() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                while (timer > 1) {
                    timer--
                    delay(3000)
                }
                loge { "Time running out, $timer" }
                _timeUsageEnded.postValue(true)
            }
        }
    }
}