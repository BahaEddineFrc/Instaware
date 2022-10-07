package com.enablon.instaware.domain.repository

import com.enablon.instaware.domain.model.quote.Quote
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface QuoteRepository {
    suspend fun getNewQuote(): Single<Response<Quote>>
}