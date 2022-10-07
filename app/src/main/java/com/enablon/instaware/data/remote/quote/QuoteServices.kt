package com.enablon.instaware.data.remote.quote

import com.enablon.instaware.data.remote.quote.QuoteEndPoints.NEW_QUOTE
import com.enablon.instaware.domain.model.quote.Quote
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Quote API services
 */
interface QuoteServices {
    @GET(NEW_QUOTE)
    fun getNewQuote(): Single<Response<Quote>>
}